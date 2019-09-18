package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class Player extends MovingUnit {
    int exp;// experience points
    PVector lookDirection;
    int expLevelUp;
    int levelUpHpIncrease;
    int expLevelUpRequirement;

    Player() {
        position = new PVector(DG.width / 2f, DG.height * 7f / 8f);// middle bottom
        targetPosition = position.copy();
        msBase = 4f;
        msCurrent = msBase;
        radius = 15;
        color = DG.color(36, 191, 83);
        dead = false;
        maxHp = 100;
        hp = maxHp;
        lvl = 0;
        exp = 0;
        lookDirection = new PVector();
        borderWidth = 1;
        healthBarLength = 50;
        expLevelUp = 60;
        levelUpHpIncrease = 50;
        expLevelUpRequirement = 60;

    }

    public void update() {
        display();
        if (position != targetPosition) {
            moveTowardsTargetPosition();
        }
        setMouseDirection();
    }

    public void setMouseDirection() {
        lookDirection = PVector.sub(new PVector(DG.mouseX, DG.mouseY), position).normalize();
    }

    public void attack() {
        DG.projectiles.add(new Projectile(PVector.add(position, PVector.mult(lookDirection, radius + 5)),
                PVector.mult(lookDirection, 10), 20, new String[] { "Enemy" }, 5, DG.color(100)));
    }

    void levelUp() {
        if (exp >= expLevelUp == true) {
            exp -= expLevelUp;
            lvl += 1;
            expLevelUp += lvl*60;
            maxHp += levelUpHpIncrease;
            hp = maxHp;
        }

    }

    void expGet(int expGain) {
        exp+=expGain;
        levelUp();
    }
}