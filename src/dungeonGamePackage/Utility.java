package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class Utility {

    static void circle(PVector position, float radius) {
        DG.ellipse(position.x, position.y, radius * 2, radius * 2);
    }

    static int sign(float x){
        if (x < 0) return -1;
        if (x > 0) return 1;
        return 0;
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

    static void spawnZoneEnemies(PVector position) {
        int numEnemyPacks = DG.floor(DG.random(8, 12));

        }
    }

    static void spawnBoss(PVector spawnPosition) {
        DG.enemies.add(new BossEnemy(spawnPosition));
    }

    static boolean contains(String[] strings, String s) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == s)
                return true;
        }
        return false;
    }
}