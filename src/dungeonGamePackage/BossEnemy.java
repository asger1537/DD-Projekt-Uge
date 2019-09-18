package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class BossEnemy extends Enemy {
    PVector targetDirection;

    BossEnemy(PVector position) {
        this.position = position;
        lvl = DG.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msBase = msCurrent;
        radius = 30;
        dead = false;
        maxHp = 300;
        hp = maxHp;
        agroRange = 100;
        dmg = 20;
        atkcd = 60;
        expReward = 50;
        healthBarLength = 60;
        targetDirection = new PVector();
    }

    @Override
    public void update() {
        display();
        checkAggro();
        if (target != null) {
            targetPosition = target.position;
        }
        if (atkcdCurrent > 1) {
            atkcdCurrent--;
        } else {
            attack();
        }
    }

    public void setTargetdirection() {
        targetDirection = PVector.sub(new PVector(targetPosition.x, targetPosition.y), position).normalize();
    }

    public void attack() {
        DG.projectiles.add(new Projectile(PVector.add(position, PVector.mult(targetDirection, radius + 10)),
                PVector.mult(targetDirection, 10), 40, new String[] { "Player" }, 5, DG.color(206, 43, 34)));
        atkcdCurrent = atkcd;
    }
}