package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

import processing.core.PVector;

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
        barrelLength=radius*3;
        barrelWidth=radius/2;
        barrelLongSide = new PVector();
        barrelShortSide = new PVector();
        lookDirection = new PVector(1,0);
        statusEffects = new ArrayList<StatusEffect>();
    }

    @Override
    public void update() {
        updateStatusEffects();
        showBarrel();
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
        DG.zone.completed = true;
    }

    @Override
    void checkAggro() {
        // set target to player if the player is in the aggro range
        float dist = PVector.dist(position, DG.p.position);
        if (dist <= agroRange) {
            target = DG.p;
        }
        // All enemies target the player if the boss is attacked
        if (hit) {
            target = DG.p;
            for(int i = 0; i < DG.zone.enemies.size(); i++){
                Enemy enemy = DG.zone.enemies.get(i);
            if(enemy == this || enemy.target == DG.p) continue;  
                    enemy.target = DG.p;        
          }
        }
    }

}