package dungeonGamePackage;


abstract class Ability{
    float scaling;//percent of player damage
    int cd, cdCurrent;

    Ability(float scaling, int cd){
        this.scaling = scaling;
        this.cd = cd*60;
        this.cdCurrent = 0;
    }

    //should be overriden
    void use(){

    }
}