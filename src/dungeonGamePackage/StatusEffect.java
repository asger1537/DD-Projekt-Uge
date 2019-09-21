package dungeonGamePackage;

class StatusEffect{
    MovingUnit target;
    int duration;

    StatusEffect(MovingUnit target, int duration){
        this.target = target;
        this.duration = duration;
    }

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