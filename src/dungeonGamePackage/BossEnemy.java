package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class BossEnemy extends RangedEnemy {

    BossEnemy(PVector position) {
        super(position);
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

    void onDeath() {
        DG.p.expGet(expReward);
        DG.zone.levelCompleted = true;
    }
}