package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

import processing.core.PVector;

class Player extends MovingUnit {
    int exp;// experience points
    int expLevelUp;
    int levelUpHpIncrease;
    int expLevelUpRequirementIncrease;
    int dmg;
    int levelUpDmgIncrease;
    int levelUpTextTime;

    Ability[] abilities;

    Player() {
        position = new PVector(radius*4,1900-radius*4); // player spawns at bottom left corner
        targetPosition = position.copy();
        msBase = 4f;
        msCurrent = msBase;
        radius = 20;
        color = DG.color(36, 191, 83);
        dead = false;
        maxHp = 100;
        hp = maxHp;
        lvl = 1;
        exp = 0;
        borderWidth = 1;
        healthBarLength = 50;
        expLevelUp = 60;
        levelUpHpIncrease = 50;
        expLevelUpRequirementIncrease = 60;
        dmg = 10;
        levelUpDmgIncrease = 10;
        levelUpTextTime = 0;
        barrelLength = radius * 3;
        barrelWidth = radius / 2;
        barrelLongSide = new PVector();
        barrelShortSide = new PVector();
        abilities = new Ability[5];
        statusEffects = new ArrayList<StatusEffect>();
    }

    public void update() {
        updateAbilityCooldowns();
        updateStatusEffects();
        setMouseDirection();
        showBarrel();
        display();
        if (position != targetPosition) {
            moveTowardsTargetPosition();
        }

        if (dead) {
            onDeath();
        }
        if (levelUpTextTime > 0) {
            DG.textSize(30);
            DG.fill(255, 185, 55);
            DG.text("LEVEL UP!", position.x, position.y - 50);
            levelUpTextTime--;
        }
    }

    public void setMouseDirection() {
        lookDirection = PVector.sub(new PVector(DG.mouseX, DG.mouseY), new PVector(DG.width / 2f, DG.height / 2f))
                .normalize();
    }

    void levelUp() {
        exp -= expLevelUp;
        lvl += 1;
        expLevelUp += lvl * expLevelUpRequirementIncrease;
        maxHp += levelUpHpIncrease;
        dmg += levelUpDmgIncrease;
        hp = maxHp;

        levelUpTextTime = 120;
    }

    void expGet(int expGain) {
        exp += expGain;
        if (exp >= expLevelUp) {
            levelUp();
        }
    }

    void onDeath() {
        DG.currentScreen = UI.deathScreen;
    }

    void updateAbilityCooldowns(){
        for (int i = 0; i < abilities.length; i++){
            if (abilities[i] != null){
                abilities[i].updateCooldown();
            }
        }
    }

    void learnAbility(Ability a, int idx){
        abilities[idx] = a;
    }
}