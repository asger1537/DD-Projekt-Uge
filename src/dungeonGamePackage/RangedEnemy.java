package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

import processing.core.PVector;

class RangedEnemy extends Enemy {

    RangedEnemy(PVector position) {
        this.position = position;
        spawnTile = Utility.gridTileAtPosition(position);
        lvl = DG.zone.currentZone;
        color = DG.color(221, 33, 6);
        msBase = 3f;
        msCurrent = msBase;
        radius = 10;
        dead = false;
        maxHp = 50+50*lvl;
        hp = maxHp;
        agroRange = 200;
        dmg = 10*lvl;
        atkcd = 60/lvl;
        atkcdCurrent = 0;
        expReward = 15*lvl;
        healthBarLength = 30;
        barrelLength=radius*3;
        barrelWidth=radius/2;
        barrelLongSide = new PVector();
        barrelShortSide = new PVector();
        lookDirection = new PVector(1,0);
        statusEffects = new ArrayList<StatusEffect>();
    }

    @Override
    public void update() {
        checkAggro();
        if (target != null) {
            targetPosition = target.position;
            if (atkcdCurrent > 1) {
                atkcdCurrent--;
            } else {
                setlookDirection();
                attack();
            }
        }
        showBarrel();
        display();
        
    }

    void setlookDirection() {
        lookDirection = PVector.sub(targetPosition, position).normalize();
    }

    void attack() {
        DG.projectiles.add(new Projectile(PVector.add(position, PVector.mult(lookDirection, radius + 10)),
                PVector.mult(lookDirection, 10), dmg, new String[] { "Player" }, 5, DG.color(206, 43, 34)));
        atkcdCurrent = atkcd;
    }
}