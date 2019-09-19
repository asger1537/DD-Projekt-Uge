package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class BossEnemy extends Enemy {

    BossEnemy(PVector position) {
        this.position = position;
        lvl = DG.zone.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msCurrent = msBase;
        radius = 30;
        dead = false;
        maxHp = 300*lvl;
        hp = maxHp;
        agroRange = 600;
        dmg = 30*lvl;
        atkcd = 60/lvl;
        atkcdCurrent = 0;
        expReward = 50*lvl;
        healthBarLength = 60;
    }

    @Override
    public void update() {
        display();
        checkAggro();
        if (target != null) {
            targetPosition = target.position;
            if (atkcdCurrent > 1) {
                atkcdCurrent--;
            } else {
                setlookDirection();
                attack();
            }
        }
    }

    void setlookDirection() {
        lookDirection = PVector.sub(targetPosition, position).normalize();
    }

    void attack() {
        DG.projectiles.add(new Projectile(PVector.add(position, PVector.mult(lookDirection, radius + 10)),
                PVector.mult(lookDirection, 10), 40, new String[] { "Player" }, 5, DG.color(206, 43, 34)));
        atkcdCurrent = atkcd;
    }

    void onDeath() {
        DG.p.expGet(expReward);
    }
}