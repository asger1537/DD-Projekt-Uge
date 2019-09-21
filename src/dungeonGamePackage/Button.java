package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;
import dungeonGamePackage.UI.ClickInterface;

class Button extends UIelement{
    float borderWidth;
    boolean hovered;
    ClickInterface onClick;
    int mode;

    Button(float x, float y, float w, float h, int color, ClickInterface onClick, int mode){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.borderWidth = 1;
        this.hovered = false;
        this.onClick = onClick;
        this.mode = mode;
    }

    @Override
    void update(){
        updateHovered();
        display();
        if (hovered){
            onHover();
        } else{
            borderWidth = 1;
        }
    }

    void display(){
        DG.fill(color);
        DG.strokeWeight(borderWidth);
        if (mode == 0) DG.rectMode(DG.CORNER);
        if (mode == 3) DG.rectMode(DG.CENTER);
        DG.rect(x, y, w, h);
        DG.strokeWeight(1);
    }

    void onHover(){
        borderWidth = 3;
    }

    void updateHovered(){
        if (mode == 0){
            hovered = DG.mouseX > x && DG.mouseX < x+w && DG.mouseY > y && DG.mouseY < y+h;
        } else{
            hovered = DG.mouseX > x - w/2f && DG.mouseX < x + w/2f + w && DG.mouseY > y - h/2f && DG.mouseY < y + h/2f;
        }
    }


}