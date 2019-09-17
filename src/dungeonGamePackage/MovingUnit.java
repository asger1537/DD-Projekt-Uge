package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

abstract class MovingUnit {
    PVector position;//center of unit
    PVector targetPosition;//the position the unit is moving towards
    float ms;//movement speed
    float radius;
    int color;
    boolean dead;
    int hp;//hit points

    void display(){
        DG.fill(color);
        Utility.circle(position, radius);    
    }
}