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
        if (DG.mouseButton == DG.RIGHT && DG.p.abilities[0].cdCurrent == 0 && DG.p.abilities[0] != null){
            DG.p.abilities[0].use();
        } 

        for (int i = 0; i < DG.currentScreen.buttons.size(); i++){
            if (DG.currentScreen.buttons.get(i).hovered){
                DG.currentScreen.buttons.get(i).onClick.use();    
            }
        }
    }

    public static void keyPressed(){
        //using abilities
        for (int i = 0; i < Keybinds.abilityKeybinds.length; i++){
            if (DG.key == Keybinds.abilityKeybinds[i] && DG.p.abilities[i+1] != null && DG.p.abilities[i+1].cdCurrent == 0 ){
                DG.p.abilities[i+1].use();
            }    
        }
    }


}