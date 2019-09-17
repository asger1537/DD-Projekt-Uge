package dungeonGamePackage;

import processing.core.PVector;

abstract class Enemy extends MovingUnit {
    float agroRange;
    int dmg;
    int atkcd;// attack cooldown
    int expReward;
    Player target;

    public void update() {

    }

    void checkAggroRange(Player p) {
        target = p;
        float dist = PVector.dist(position, target.position);
        if (dist >= agroRange) {
            targetPosition = target.position;
        }
    }
}