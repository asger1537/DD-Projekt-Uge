package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class BossEnemy extends Enemy{

    BossEnemy(PVector position){
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
    }
}