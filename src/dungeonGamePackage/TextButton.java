package dungeonGamePackage;

import dungeonGamePackage.UI.ClickInterface;
import static dungeonGamePackage.DungeonGame.DG;

class TextButton extends Button{
    String text;
    int textColor;

    TextButton(float x, float y, float w, float h, int color,
     String text, int textColor, ClickInterface onClick){
        super(x, y, w, h, color, onClick);
        this.borderWidth = 1;
        this.text = text;
        this.textColor = textColor;
        this.hovered = false;
    }

    void display(){
        DG.fill(color);
        DG.strokeWeight(borderWidth);
        DG.rect(x, y, w, h);
        
        DG.textMode(DG.CENTER);
        DG.fill(textColor);
        DG.text(text, x + w/2f, y + h*0.8f);
    }


}