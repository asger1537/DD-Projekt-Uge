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
        color = DG.color(36, 191, 83);
        dead = false;
        hp = 100;
        expReward = 0;
        agroRange = 200;
        dmg = 10;
        atkcd = 60;
        expReward = 20;
    }

    public void update(){
        display();
        if (target != null){
            targetPosition = target.position;
        }
    }



}