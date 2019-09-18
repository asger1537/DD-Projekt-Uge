package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;
import processing.core.PVector;

abstract class MovingUnit {
    PVector position;// center of unit
    PVector targetPosition;// the position the unit is moving towards
    float ms;// movement speed
    float radius;
    int color;
    boolean dead;
    int hp;// hit points
    int maxHp; // max hit points
    int lvl;
    float borderColor;
    float borderWidth;
    float healthBarLength;

    void display() {
        showHealthBar();
        DG.fill(color);
        Utility.circle(position, radius);
    }

    void takeDamage(int d) {
        hp -= d; // resulting hp after damage is hp-damage taken (d)
        // if hp is 0 or less than 0 then you're dead
        if (hp <= 0) {
            dead = true;
        }
    }

    public void moveTowardsTargetPosition() {
        // the direction of the vector from position pointing to target position
        PVector dir = PVector.sub(targetPosition, position).normalize();

        if (PVector.dist(position, targetPosition) > ms) {
            position.add(PVector.mult(dir, ms));
        } else {
            position = targetPosition.copy();
        }
    }

    void onDeath() {
    }

    void showHealthBar() {
        float healthPercent = (hp / maxHp) * 100;
        int healthBarColor = DG.color(0, 0, 0);
        if (healthPercent > 50) {
            healthBarColor = DG.color((int) (255 - healthPercent * 2.55), 255, 0);
        }
        if (healthPercent <= 50) {
            healthBarColor = DG.color(255, (int) (healthPercent * 2.55), 0);
        }
        DG.fill(healthBarColor);
        DG.stroke(borderColor);
        DG.strokeWeight(borderWidth);
        // the length of the healthBar
        float l = healthBarLength * hp / maxHp;
        // makes sure the healthbar doesn't get negative length
        if (l < 0)
            l = 0;
        DG.rect(position.x - healthBarLength / 2, position.y - 50, l, 10);
        DG.stroke(0);
        DG.strokeWeight(1);
    }
}