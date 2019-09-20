package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;
import processing.core.PVector;

class Zone {
    boolean levelCompleted;
    int currentZone;
    ArrayList<Enemy> enemies;
    float width, height;

    Zone(float width, float height) {
        this.width = width;
        this.height = height;
        currentZone = 1;
        enemies = new ArrayList<Enemy>();
        levelCompleted = false;
    }

    void display() {
        DG.background(0);

        for (int y = 0; y < height; y += 50) {
            for (int x = 0; x < width; x += 50) {
                DG.image(DG.floorTileImg, x, y);
            }
        }

    }

    void spawnEnemyCluster(PVector position, int numEnemies) {
        PVector spawnVector = new PVector(30, 0);

        for (int i = 0; i < numEnemies; i++) {
            PVector spawnPosition;
            int mobTypeID = DG.floor(DG.random(0, 2));//melee or ranged
            
            // the first enemy is just spawned at the position, but the subsequent enemies
            // are spawned around the first enemy with some randomness
            if (i == 0) {
                spawnPosition = position;
            } else {
                spawnPosition = PVector.add(position, spawnVector);
            }
            spawnVector.rotate(2 * DG.PI / numEnemies * DG.random(0.8f, 1.2f));
            if (mobTypeID == 0) {
                enemies.add(new MeleeEnemy(spawnPosition));
            } else {
                enemies.add(new RangedEnemy(spawnPosition));
            }
        }
    }

    void spawnZoneEnemies() {
        int numEnemyPacks = DG.floor(DG.random(1 * currentZone, 2 * currentZone));
        PVector spawnPosition;
        int maxDistance = 250;

        for (int i = 0; i < numEnemyPacks; i++) {
            packID = i;
            int x = (int) DG.random(300, width - 300);
            int y = (int) DG.random(height / 4, height - height / 4);
            spawnPosition = new PVector(x, y);
            for (int j = 0; j < enemies.size(); j++) {
                int maxTries = 100;
                while (maxTries > 0 && PVector.dist(spawnPosition, enemies.get(j).position) < maxDistance) {
                    x = (int) DG.random(10, width - 10);
                    y = (int) DG.random(height / 4, height - height / 4);
                    spawnPosition = new PVector(x, y);
                    --maxTries;
                }
            }
            spawnEnemyCluster(spawnPosition, DG.floor(DG.random(3, 6)));
        }

        spawnBoss(new PVector(DG.width / 2, DG.height / 5));
    }

    void spawnBoss(PVector spawnPosition) {
        enemies.add(new BossEnemy(spawnPosition));
    }

    void update() {
        display();
        if (levelCompleted) {
            portalTonextLevel();
        }
        //updating enemies and removing dead ones
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).dead) {
                enemies.get(i).onDeath();
                enemies.remove(i);
            }
        }
    }

    void generateNewZone() {
        currentZone += 1;
        levelCompleted = false;
        DG.p.position = new PVector(100, height-10); // player spawns at buttom left corner
        spawnZoneEnemies();
    }

    void portalTonextLevel() {
        int portalRadius = 100;
        PVector portalPosition = new PVector(DG.width / 2, DG.height / 5);
        DG.fill(0, 0, 255);
        DG.imageMode(DG.CENTER);
        DG.image(DG.portalImg, portalPosition.x, portalPosition.y);
        if (PVector.dist(portalPosition, DG.p.position) < portalRadius + DG.p.radius) {
            generateNewZone();
        }

        DG.textAlign(DG.CENTER);
        DG.fill(0);
        DG.text(String.format("ZONE %d UNLOCKED", currentZone + 1), portalPosition.x,
                portalPosition.y - portalRadius * 1.2f);
    }
}