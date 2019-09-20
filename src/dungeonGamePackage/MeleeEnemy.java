package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class MeleeEnemy extends Enemy {

    MeleeEnemy(PVector position, int packID) {
        this.position = position;
        this.packID = packID;
        lvl = DG.zone.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msCurrent = msBase;
        radius = 10;
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
    
    @Override
    void checkAggro() {
        // set target to player if the player is in the aggro range
        float dist = PVector.dist(position, DG.p.position);
        if (dist <= agroRange) {
            target = DG.p;
        }
        // set target to player if the enemy takes damage
        if (hit) {
            for(int i = 0; i < DG.zone.enemies.size(); i++){
                if(DG.zone.enemies.get(i).packID == packID){
                    DG.zone.enemies.get(i).target = DG.p;
                }
            }
        }
    }
}