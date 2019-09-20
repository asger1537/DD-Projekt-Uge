package dungeonGamePackage;

//A collection of the ability anonymous classes 
import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Abilities{
    Ability chainLightningShot = new Ability(1.5f, 10){
        @Override
        void use(){
            DG.projectiles.add(new Projectile(PVector.add(DG.p.position, DG.p.barrelLongSide), DG.p.lookDirection, (int)(scaling*DG.p.dmg),
             new String[]{"Enemy"}, 10, DG.color(30)){
                int numChains = 3;
                int chainCount = 0;
                @Override
                void onEnemyCollision(Enemy e){
                    e.hit = true;
                    chainCount++;
                    
                }

             });
        }
    };


}