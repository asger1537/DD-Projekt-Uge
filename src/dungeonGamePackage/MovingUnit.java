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
    int lvl;

    void display(){
        DG.fill(color);
        Utility.circle(position, radius);    
    }

    void takeDamage(int d){
       hp -= d; // resulting hp after damage is hp-damage taken (d)
       // if hp is 0 or less than 0 then you're dead
       if(hp <= 0){ 
        dead = true;
       }
     }


    void moveTowardsTargetPosition(){
        //the direction of the vector from position pointing to target position
        PVector dir = PVector.sub(targetPosition, position).normalize();

        if (PVector.dist(position, targetPosition) > ms){
            position.add(PVector.mult(dir, ms));
        } else{
            position = targetPosition.copy();
        }
    }
}