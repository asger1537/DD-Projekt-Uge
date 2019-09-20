package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class MeleeEnemy extends Enemy {

    MeleeEnemy(PVector position) {
        this.position = position;
        spawnTile = Utility.gridTileAtPosition(position);
        lvl = DG.zone.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msCurrent = msBase;
        radius = 30;
        dead = false;
        maxHp = 50+50*lvl;
        hp = maxHp;
        agroRange = 200;
        dmg = 10*lvl;
        atkcd = 60;
        atkcdCurrent = 0;
        expReward = 10*lvl;
        healthBarLength = 30;
    }

    @Override
    public void update() {
        display();
        checkAggro();
        if (target != null) {
            targetPosition = target.position;
            moveTowardsTargetPosition();

            if (atkcdCurrent > 1) {
                atkcdCurrent--;
                msCurrent = msBase * 0.75f;
            } else {
                checkPlayerCollision();
                msCurrent = msBase;
            }
        }
    }

    @Override
    void display(){
        DG.imageMode(DG.CENTER);
        DG.image(DG.meleeEnemyImg, position.x, position.y);
        showHealthBar();
    }

    @Override
    void attack() {
        DG.p.takeDamage(dmg);
        atkcdCurrent = atkcd;
    }

    void checkPlayerCollision() {
        if (PVector.dist(position, target.position) < radius + target.radius) {
            attack();
        }
    }
    
}