package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Input{

    public static void mousePressed(){

        float mx, my;
        //reverting the translation
        mx = DG.mouseX -DG.width/2f + DG.p.position.x;
        my = DG.mouseY -DG.height/2f + DG.p.position.y;

        //set target position on left click
        if (DG.mouseButton == DG.LEFT){
            if (mx > DG.p.radius && mx < DG.zone.width - DG.p.radius &&
                my > DG.p.radius && my < DG.zone.height - DG.p.radius){
                    DG.p.targetPosition = new PVector(mx, my);
                }
        }
        //shoot
        if (DG.mouseButton == DG.RIGHT){
            DG.p.attack();
        }

        for (int i = 0; i < DG.currentScreen.buttons.size(); i++){
            if (DG.currentScreen.buttons.get(i).hovered){
                DG.currentScreen.buttons.get(i).onClick.use();    
            }
        }
    }

    public static void keyPressed(){
        //using abilities
        if (DG.key == 'q'){
            Abilities.chainLightningShot.use();
        } 
        if (DG.key == 'w'){
            
        }
        if (DG.key == 'e'){
            
        }
        if (DG.key == 'r'){
            
        }
    }


}