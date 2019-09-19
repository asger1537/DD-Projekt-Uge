package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class MeleeEnemy extends Enemy {

    MeleeEnemy(PVector position) {
        this.position = position;
        lvl = DG.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msCurrent = msBase;
        radius = 10;
        dead = false;
        maxHp = 100;
        hp = maxHp;
        agroRange = 200;
        dmg = 10;
        atkcd = 60;
        atkcdCurrent = 0;
        expReward = 20;
        healthBarLength = 30;
    }

    @Override
    public void update() {
        display(position);
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

    void onDeath() {
        DG.p.expGet(expReward);
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