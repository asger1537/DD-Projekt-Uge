package dungeonGamePackage;

abstract class Enemy extends MovingUnit{
    float agroRange;
    int dmg;
    int atkcd;//attack cooldown
    int expReward;
    Player target;

    
}