package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

class Particle{
    int duration;

    Particle(int duration){
        this.duration = duration;
    }

    //to be overriden
    void display(){

    }

    void update(){
        display();
        duration--;
        if (duration == 0){
            DG.particles.remove(this);
        }
    }

}