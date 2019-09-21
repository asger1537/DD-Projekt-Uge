package dungeonGamePackage;

import processing.core.PImage;

abstract class Ability {
    float scaling;//percent of player damage
    int cd, cdCurrent;
    PImage icon;

    Ability(float scaling, float cd, PImage icon){
        this.scaling = scaling;
        this.cd = (int)(cd*60);
        this.cdCurrent = 0;
        this.icon = icon;
    }

    //should be overriden
    void use(){
    }

    void updateCooldown(){
        if (cdCurrent > 0){
            cdCurrent--;
        }
    }
}