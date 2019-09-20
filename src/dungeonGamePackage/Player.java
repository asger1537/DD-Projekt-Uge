package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class Player extends MovingUnit {
    int exp;// experience points
    int expLevelUp;
    int levelUpHpIncrease;
    int expLevelUpRequirementIncrease;
    int dmg;
    int levelUpDmgIncrease;
    int levelUpTextTime;

    Player() {
        position = new PVector(DG.width / 2f, DG.height * 7f / 8f);// middle bottom
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
        dmg = 5;
        levelUpDmgIncrease = 10;
        levelUpTextTime = 0;
    }

    public void update() {
        display();
        if (position != targetPosition) {
            moveTowardsTargetPosition();
        }
        setMouseDirection();
        if (dead) {
            onDeath();
        }
        if (levelUpTextTime > 0){
            DG.textSize(30);
            DG.fill(255, 185, 55);
            DG.text("LEVEL UP!", position.x, position.y-50);
            levelUpTextTime--;
        }
    }

    public void setMouseDirection() {        
        lookDirection = PVector.sub(new PVector(DG.mouseX, DG.mouseY), new PVector(DG.width/2f, DG.height/2f)).normalize();
    }

    public void attack() {
        DG.projectiles.add(new Projectile(PVector.add(position, PVector.mult(lookDirection, radius + 5)),
                PVector.mult(lookDirection, 10), dmg, new String[] { "Enemy" }, 5, DG.color(100)));
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
}