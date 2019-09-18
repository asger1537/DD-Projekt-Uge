package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class MeleeEnemy extends Enemy {

    MeleeEnemy(PVector position){
        this.position = position;
        lvl = DG.currentZone;
        color = DG.color(221, 33, 6);
        ms = 3f;
        radius = 10;
        dead = false;
        maxHp = 100;
        hp = maxHp;
        agroRange = 200;
        dmg = 10;
        atkcd = 60;
        expReward = 20;
        healthBarLength = 30;
    }

    @Override
    public void update(){
        display();
        checkAggro();
        if (target != null){
            targetPosition = target.position;
            moveTowardsTargetPosition();
        }
    }

    void onDeath(){
     DG.p.exp += expReward;
    }
}