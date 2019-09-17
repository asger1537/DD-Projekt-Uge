package dungeonGamePackage;

import java.awt.Color;
import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Projectile {
    PVector position;
    PVector velocity;
    float speed;
    int dmg;
    String[] canHit;
    float radius;
    int color;

    Projectile(PVector position, PVector velocity, float speed, int dmg,
    String[] canHit, float radius, int color) {
        this.position = position;
        this.velocity = velocity;
        this.speed = speed;
        this.dmg = dmg;
        this.canHit = canHit;
        this.radius = radius;
        this.color = color;
    }

    boolean[] checkEdgeCollision() {
        boolean xCollision = false;
        boolean yCollision = false;
        if (position.x + radius >= DG.width || position.x - radius <= 0) {
            xCollision = true;
        } else {
            xCollision = false;
        }
        if (position.y + radius >= DG.height || position.y - radius <= 0) {
            yCollision = true;
        } else {
            yCollision = false;
        }
        return new boolean[] {xCollision,yCollision};

    }

}
