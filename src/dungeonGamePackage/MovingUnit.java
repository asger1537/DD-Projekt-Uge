package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;
import processing.core.PVector;

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

    void moveTowards(){
        //the direction of the vector from position pointing to target position
        PVector dir = PVector.sub(targetPosition, position).normalize();

        if (PVector.dist(position, targetPosition) > ms){
            position.add(PVector.mult(dir, ms));
        } else{
            position = targetPosition.copy();
        }
    }
}