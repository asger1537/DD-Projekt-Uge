package dungeonGamePackage;

import dungeonGamePackage.UI.ClickInterface;
import static dungeonGamePackage.DungeonGame.DG;

class TextButton extends Button{
    String text;
    int textColor, fontSize;

    TextButton(float x, float y, float w, float h, int color,
     String text, int textColor, int fontSize, ClickInterface onClick){
        super(x, y, w, h, color, onClick);
        this.borderWidth = 1;
        this.text = text;
        this.textColor = textColor;
        this.fontSize = fontSize;
        this.hovered = false;
    }

    void display(){
        DG.fill(color);
        DG.strokeWeight(borderWidth);
        DG.rectMode(DG.CENTER);
        DG.rect(x, y, w, h);
        DG.textAlign(DG.CENTER);
        DG.fill(textColor);
        DG.textSize(fontSize);
        DG.text(text, x, y+h*0.3f);
    }


}