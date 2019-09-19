package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

class Zone {
    boolean levelCompleted;
    int currentZone;
    ArrayList<Enemy> enemies;

     void initializeZoneVariables() {
        currentZone = 1;
        enemies = new ArrayList<Enemy>();
    }

    static void spawnEnemyCluster(PVector position) {
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
            DG.enemies.add(new MeleeEnemy(spawnPosition));
        }
    }

    static void spawnZoneEnemies() {
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

    static void spawnBoss(PVector spawnPosition) {
        DG.enemies.add(new BossEnemy(spawnPosition));
    }

    void update(){
        
    }

}