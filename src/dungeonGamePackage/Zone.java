package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

class Zone {
    boolean levelCompleted;
    int currentZone;
    ArrayList<Enemy> enemies;
    boolean bossHasSpawned;

        Zone() {
        currentZone = 1;
        enemies = new ArrayList<Enemy>();
        bossHasSpawned = false;
        levelCompleted = false;
    }

    void spawnEnemyCluster(PVector position) {
        int numEnemies = DG.floor(DG.random(3, 6));
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
        int numEnemyPacks = DG.floor(DG.random(8, 12));
        PVector spawnPosition;
        int maxDistance;

        for (int i = 0; i < numEnemyPacks; i++) {
            int x = (int) DG.random(10, DG.width - 10);
            int y = (int) DG.random(DG.height / 4, DG.height - DG.height / 4);
            spawnPosition = new PVector(x, y);
            spawnEnemyCluster(spawnPosition);
        }
    }

    void spawnBoss(PVector spawnPosition) {
        enemies.add(new BossEnemy(spawnPosition));
    }

    void update() {
        if (enemies.size() == 0 && !bossHasSpawned) {
            spawnBoss(new PVector(DG.width / 2, DG.height / 5));
            bossHasSpawned = true;
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
        spawnZoneEnemies();
    }
}