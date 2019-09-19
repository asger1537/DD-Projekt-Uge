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
        hovered = false;
        onClick = this.onClick;
    }

    @Override
    void update(){
        updateHovered();
        show();
        if (hovered){
            onHover();
        }
    }

    void show(){
        DG.fill(color);
        DG.strokeWeight(borderWidth);
        DG.rect(x, y, w, h);
        DG.strokeWeight(1);
    }

    void onHover(){
        borderWidth = 3;
    }

    void updateHovered(){
        hovered = DG.mouseX > x && DG.mouseX < x + w && DG.mouseY > y && DG.mouseY < y + h;
    }


}