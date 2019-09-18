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

    void checkAggroRange() {
        float dist = PVector.dist(position, DG.p.position);
        if (dist <= agroRange) {
            target = DG.p;
        }
    }

    void attack(){
        DG.p.takeDamage(dmg);
        atkcdCurrent = atkcd;
    }
}