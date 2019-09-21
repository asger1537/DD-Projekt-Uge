package dungeonGamePackage;

import java.util.ArrayList;

import processing.core.PImage;
import static dungeonGamePackage.DungeonGame.DG;

abstract class Ability {
    float scaling;//percent of player damage
    int cd, cdCurrent;
    PImage icon;
    String description;

    Ability(float scaling, float cd, PImage icon){
        this.scaling = scaling;
        this.cd = (int)(cd*60);
        this.cdCurrent = 0;
        this.icon = icon;
        updateDescription();
    }

    //should be overriden
    void use(){
    }

    void updateCooldown(){
        if (cdCurrent > 0){
            cdCurrent--;
        }
    }

    //should be overridden
    void updateDescription(){
        
    }

    void formatDescription(){
        DG.textSize(12);
        String[] words = description.split(" ");
        ArrayList<String> formatted = new ArrayList<String>();
        float currentTextWidth = 0;
        float textWidthThreshhold = 60;
        for (String w : words){
            currentTextWidth += DG.textWidth(w+" ");
            if (currentTextWidth > textWidthThreshhold){
            currentTextWidth = 0;
            formatted.add(w+"\n");
            } else{
            formatted.add(w);
            }
        }
        description = String.join(" ", formatted);    
    }
}