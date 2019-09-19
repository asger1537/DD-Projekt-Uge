package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;
import dungeonGamePackage.UI.ClickInterface;

class Button extends UIelement{
    float borderWidth;
    boolean hovered;
    ClickInterface onClick;

    Button(float x, float y, float w, float h, int color, ClickInterface onClick){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.borderWidth = 1;
        this.hovered = false;
        this.onClick = onClick;
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
        DG.rectMode(DG.CENTER);
        DG.rect(x, y, w, h);
        DG.strokeWeight(1);
    }

    void onHover(){
        borderWidth = 3;
    }

    void updateHovered(){
        hovered = DG.mouseX > x - w/2f && DG.mouseX < x + w/2f + w && DG.mouseY > y - h/2f && DG.mouseY < y + h/2f;
    }


}