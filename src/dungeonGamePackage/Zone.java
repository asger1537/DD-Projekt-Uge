package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;
import processing.core.PVector;


class Zone {
    boolean completed;
    int currentZone;
    ArrayList<Enemy> enemies;
    int width, height;
    GridTile[][] tiles;
    static int tileSize = 50;
    int tileRows, tileCols;

    Zone(int width, int height, int zoneLevel) {
        this.width = width;
        this.height = height;
        this.currentZone = zoneLevel;
        this.tileRows = height/tileSize;
        this.tileCols = width/tileSize;
        completed = false;

        tiles = new GridTile[tileRows][tileCols];
        for (int i = 0; i < tileRows; i++){
            for (int j = 0; j < tileCols; j++){
                tiles[i][j] = new GridTile(new PVector(j*tileSize, i*tileSize), i, j, false, DG.floorTileImg);
            }
        }

        enemies = new ArrayList<Enemy>();
    }

    void display() {
        DG.background(0);

        for (int i = 0; i < tileRows; i++){
            for (int j = 0; j < tileCols; j++){
                tiles[i][j].display();
            }
        }

    }

    void spawnEnemyCluster(PVector position, int numEnemies) {
        PVector spawnVector = new PVector(100, 0);

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
        if (completed) {
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
        DG.zone = new Zone(2000 + DG.floor(DG.random(0, 20))*tileSize, 2000 + DG.floor(DG.random(0, 20))*tileSize, currentZone +1);
        DG.p.position = new PVector(DG.zone.width/2,DG.zone.height-DG.zone.height/5); //player spawns in the bottom middle of the map
        DG.zone.spawnZoneEnemies();
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