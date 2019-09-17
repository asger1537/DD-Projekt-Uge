package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Input{

    public static void mousePressed(){
        //set target position on left click
        if (DG.mouseButton == DG.LEFT){
            if (DG.mouseX > DG.p.radius && DG.mouseX < DG.width-DG.p.radius &&
                DG.mouseY > DG.p.radius && DG.mouseY < DG.height-DG.p.radius){
                    DG.p.targetPosition = new PVector(DG.mouseX, DG.mouseY);
                }
        }
        //shoot
        if (DG.mouseButton == DG.RIGHT){
            DG.p.attack();
        }
    }

    public static void keyPressed(){
        //using abilities
        if (DG.key == 'q'){

        } 
        if (DG.key == 'w'){
            
        }
        if (DG.key == 'e'){
            
        }
        if (DG.key == 'r'){
            
        }
    }


}