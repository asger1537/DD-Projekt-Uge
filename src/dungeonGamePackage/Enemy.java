package dungeonGamePackage;
import static dungeonGamePackage.DungeonGame.DG;
import processing.core.PVector;

abstract class Enemy extends MovingUnit {
    float agroRange;
    int dmg;
    int atkcd;// attack cooldown
    int atkcdCurrent;//used to count down
    int expReward;
    Player target;

    public void update() {

    }

    void checkAggro() {
        // set target to player if the player is in the aggro range
        float dist = PVector.dist(position, DG.p.position);
        if (dist <= agroRange) {
            target = DG.p;
        }
        // set target to player if the enemy takes damage
        if(hp != maxHp){
            target = DG.p;
        }
    }

    void attack(){
    }
}