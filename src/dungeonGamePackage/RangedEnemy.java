package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class RangedEnemy extends Enemy {

    RangedEnemy(PVector position, int packID) {
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
        atkcd = 60/lvl;
        atkcdCurrent = 0;
        expReward = 10*lvl;
        healthBarLength = 30;
        barrelLength=radius*3;
        barrelWidth=radius/2;
        barrelLongSide = new PVector();
        barrelShortSide = new PVector();
        lookDirection = new PVector(1,0);
    }

    @Override
    public void update() {
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
        showBarrel();
        display();
        
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