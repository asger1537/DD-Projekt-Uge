package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;
import processing.core.PVector;

class Zone {
    boolean levelCompleted;
    int currentZone;
    ArrayList<Enemy> enemies;
    boolean bossHasSpawned;

    float width, height;

    Zone(float width, float height) {
        this.width = width;
        this.height = height;
        currentZone = 1;
        enemies = new ArrayList<Enemy>();
        bossHasSpawned = false;
        levelCompleted = false;
    }

    void display(){
        DG.background(0);
        
        for (int y = 0; y < height; y += 50){
            for (int x = 0; x < width; x += 50){
                DG.image(DG.floorTile, x , y);
            }
        }
        
    }


    void spawnEnemyCluster(PVector position) {
        int numEnemies = DG.floor(DG.random(3,6));
        PVector spawnVector = new PVector(30, 0);

        for (int i = 0; i < numEnemies; i++) {
            PVector spawnPosition;

            // the first enemy is just spawned at the position, but the subsequent enemies
            // are spawned around
            // the first enemy with some randomness
            if (i == 0) {
                spawnPosition = position;
            } else {
                spawnPosition = PVector.add(position, spawnVector);
            }
            spawnVector.rotate(2 * DG.PI / numEnemies * DG.random(0.8f, 1.2f));
            enemies.add(new MeleeEnemy(spawnPosition));
        }
    }

    void spawnZoneEnemies() {
        int numEnemyPacks = DG.floor(DG.random(1*currentZone, 2*currentZone));
        PVector spawnPosition;
        int maxDistance = 250;

        for (int i = 0; i < numEnemyPacks; i++) {
            int x = (int) DG.random(10, DG.width - 10);
            int y = (int) DG.random(DG.height / 4, DG.height - DG.height / 4);
            spawnPosition = new PVector(x, y);
            for(int j = 0; j < enemies.size(); j++){
                int maxTries = 100;
                while(maxTries > 0 && PVector.dist(spawnPosition, enemies.get(j).position) < maxDistance){
                     x = (int) DG.random(10, DG.width - 10);
                     y = (int) DG.random(DG.height / 4, DG.height - DG.height / 4);
                    spawnPosition = new PVector(x, y); 
                    --maxTries;
                }
            }
            spawnEnemyCluster(spawnPosition);
        }
    }

    void spawnBoss(PVector spawnPosition) {
        enemies.add(new BossEnemy(spawnPosition));
    }

    void update() {
        display();

        if (enemies.size() == 0 && !bossHasSpawned) {
            spawnBoss(new PVector(DG.width / 2, DG.height / 5));
            bossHasSpawned = true;
        }
        if(levelCompleted){
            portalTonextLevel();
        }
        if(enemies.size() == 0 && bossHasSpawned){
            levelCompleted = true;
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).dead) {
                enemies.get(i).onDeath();
                enemies.remove(i);
            }
        }
    }

    void generateNewZone(){
        currentZone += 1;
        bossHasSpawned = false;
        levelCompleted = false;
        DG.p.position = new PVector(DG.width / 2f, DG.height * 7f / 8f); // player is moved to middle bottom of the screen when a new level starts
        spawnZoneEnemies();
    }

    void portalTonextLevel(){
        int portalRadius = 100;
        PVector portalPosition = new PVector(DG.width/2,DG.height/5);
        DG.fill(0,0,255);
        DG.imageMode(DG.CENTER);
        DG.image(DG.portal, portalPosition.x, portalPosition.y);
        if (PVector.dist(portalPosition, DG.p.position) < portalRadius + DG.p.radius) {
            generateNewZone();
        }

        DG.textAlign(DG.CENTER);
        DG.fill(0);
        DG.text(String.format("ZONE %d UNLOCKED", currentZone + 1), portalPosition.x, portalPosition.y - portalRadius*1.2f);
    }
}