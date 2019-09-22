package dungeonGamePackage;

import java.util.ArrayList;
import java.util.List;
import static dungeonGamePackage.DungeonGame.DG;

class StatusEffects {
    
    static class SpeedEffect extends StatusEffect{
        float percentage;
        SpeedEffect(float duration, MovingUnit target, float percentage){
            this.duration = (int)(duration*60);
            this.target = target;
            this.percentage = percentage;
        }
        @Override
        void effect(){
            target.msCurrent = target.msBase*percentage;
        }
    }


    


}