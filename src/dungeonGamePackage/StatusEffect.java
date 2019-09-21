package dungeonGamePackage;

abstract class StatusEffect{
    MovingUnit target;
    int duration;


    void update(){
        effect();
        duration--;
        if (duration == 0){
            target.statusEffects.remove(this);
        }
    }

    //to be overrriden
    void effect(){

    }

}