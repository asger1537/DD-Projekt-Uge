package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class Player extends MovingUnit {
    int exp;//experience points

    Player(){
        position = new PVector(DG.width/2f, DG.height*7f/8f);//middle bottom
        targetPosition = new PVector();
        ms = 4f;
        radius = 15;
        color = DG.color(36, 191, 83);
        dead = false;
        hp = 100;
        lvl = 0;
        exp = 0;
    }

    public void update(){
        display();
        if (position != targetPosition){
            moveTowardsTargetPosition();
        }
    }

    public void attack(){
        //to do
    }
}