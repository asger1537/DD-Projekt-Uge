package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

class AbilityButton extends ImageButton{
    String abilityDescription;
    Ability ability;

    AbilityButton(Ability a, int idx){
        super(UI.abilityBarX+UI.abilityBarHeight*idx, UI.abilityBarY, UI.abilityBarHeight, UI.abilityBarHeight, 0, null, DG.CORNER, a.icon);
        this.ability = a;
        abilityDescription = ability.description;
    }

    @Override
    void onHover(){
        showAbilityDescription();
    }

    void showAbilityDescription(){
        DG.fill(50);
        DG.rect(x, y-UI.expBarHeight, w, -h*2);   
        DG.textAlign(DG.CENTER);
        DG.fill(255);
        DG.textSize(12);
        DG.text(ability.description, x+w/2f, y-UI.expBarHeight-h*1.8f);
    }

}