package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;
import processing.core.PVector;

abstract class MovingUnit {
    PVector position;// center of unit
    PVector targetPosition;// the position the unit is moving towards
    PVector dir; // direction of unit's movement
    PVector lookDirection; // direction of unit's target
    PVector v1, v2, v3, v4;
    PVector barrelLongSide;
    PVector barrelShortSide;
    float msBase;
    float msCurrent;
    float radius;
    int color;
    boolean dead;
    int hp;// hit points
    int maxHp; // max hit points
    int lvl;
    float borderColor;
    float borderWidth;
    float healthBarLength;
    float barrelLength;
    float barrelWidth;
    int atkcd;// attack cooldown
    int atkcdCurrent;// used to count down

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
        dir = PVector.sub(targetPosition, position).normalize();

        if (PVector.dist(position, targetPosition) > msCurrent) {
            position.add(PVector.mult(dir, msCurrent));
        } else {
            position = targetPosition.copy();
        }
    }

    void onDeath() {
    }

    void showHealthBar() {
        String healthbarContent = hp + "/" + maxHp;
        float healthPercent = ((float) hp / (float) maxHp) * 100f;
        int healthBarColor = DG.color(0, 0, 0);
        if (healthPercent > 50) {
            healthBarColor = DG.color((int) 2 * (-2.55f * healthPercent + 255), 255, 0);
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
        DG.fill(0);
        DG.textSize(8);
        DG.text(healthbarContent, position.x, position.y - 42);
        DG.stroke(0);
        DG.strokeWeight(1);
    }

    void showBarrel() {
        //if (lookDirection != null) {
            DG.fill(0);
            // drawing the barrel
            barrelLongSide = PVector.mult(lookDirection, barrelLength);
            barrelShortSide = new PVector(-lookDirection.y, lookDirection.x);
            DG.beginShape();
            v1 = PVector.add(position, PVector.mult(barrelShortSide, barrelWidth));
            v2 = PVector.add(position, PVector.mult(barrelShortSide, -barrelWidth));
            v3 = PVector.add(v2, barrelLongSide);
            v4 = PVector.add(v1, barrelLongSide);
            // DG.pushMatrix();
            // DG.translate(position.x, position.y);
            DG.vertex(v1.x, v1.y);
            DG.vertex(v2.x, v2.y);
            DG.vertex(v3.x, v3.y);
            DG.vertex(v4.x, v4.y);
            DG.endShape(2);
            // DG.popMatrix();
        }
    }
//}