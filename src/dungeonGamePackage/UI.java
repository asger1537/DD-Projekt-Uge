package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

abstract class UI{
    float abilityBarX, abilityBarY, abilityBarLength, abilityBarHeight, abilityBarTextHeight;

    abilityBarTextHeight = 20f;
    abilityBarLength = DG.width*0.25f;
    abilityBarX = DG.width/2f-abilityBarLength/2f;//from center
    abilityBarHeight = abilityBarLength/5f;
    abilityBarY = DG.height-abilityBarTextHeight-abilityBarHeight;


    void showAbilityBar(){
        DG.rect(abilityBarX, abilityBarY, abilityBarLength, abilityBarLength);
        DG.noFill();

        for (float x = abilityBarX; x < abilityBarX + abilityBarLength; x += abilityBarLength/5f){
            DG.line(x, abilityBarY, x, abilityBarY + abilityBarHeight);
        }


    }
    
    float expBarX, expBarY, expBarLength, expBarHeight;

    expBarLength = abilityBarLength;
    expBarHeight = 30f;
    expBarX = DG.width*0.2f;
    expBarY = abilityBarY - expBarHeight;

    void showExpBar(){
        DG.rect(expBarX, expBarY, expBarLength, expBarHeight);//drawing the bar
        DG.fill(88, 29, 169);
        DG.rect(expBarX, expBarY, expBarLength*DG.p.exp/DG.p.expLevelUp, expBarHeight);    
        
        //dividing the expbar into 10 parts
        for (float x = expBarX; x < expBarX+expBarLength; x += expBarLength/10f){
            DG.line(x, expBarY, x, expBarY + expBarHeight);
        }
    }


}