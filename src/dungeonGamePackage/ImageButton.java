package dungeonGamePackage;

import dungeonGamePackage.UI.ClickInterface;
import processing.core.PImage;
import static dungeonGamePackage.DungeonGame.DG;

class ImageButton extends Button {

    PImage img;

    ImageButton(float x, float y, float w, float h, int color, ClickInterface onClick, int mode, PImage img){
        super(x, y, w, h, color, onClick, mode);
        this.img = img;
    }
    
    void display(){
        DG.imageMode(DG.CORNER);
        DG.image(img, x, y);
    }



}