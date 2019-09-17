package dungeonGamePackage;

import java.awt.Color;
import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Projectile {
    PVector position;
    PVector velocity;
    float speed;
    int dmg;
    String[] canHit;
    float radius;
    Color color;

    Projectile() {

    }
    void move() {
        position.add(velocity);
    }
    boolean[] checkEdgeCollision() {
        boolean xCollision = false;
        boolean yCollision = false;
        if (position.x + radius >= DG.width || position.x - radius <= 0) {
            xCollision = true;
        } else {
            xCollision = false;
        }
        if (position.y + radius >= DG.height || position.y - radius <= 0) {
            yCollision = true;
        } else {
            yCollision = false;
        }
        return new boolean[] {xCollision,yCollision};
    }

    void onEdgeCollsion(){
        //if it collides with the left and right walls reverse x-velocity
        if(checkEdgeCollision()[0]){
            velocity.x *= -1;
        }

        //if it collides with bottom and top walls reverse y-velocity
        if(checkEdgeCollision()[1]){
            velocity.y *= -1;
        }
    }
}
