package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Projectile {
    PVector position;
    PVector velocity;
    int dmg;
    String[] canHit;
    float radius;
    int color;
    boolean hit;

    Projectile(PVector position, PVector velocity, int dmg,
    String[] canHit, float radius, int color) {
        this.position = position;
        this.velocity = velocity;
        this.dmg = dmg;
        this.canHit = canHit;
        this.radius = radius;
        this.color = color;
    }

    void update() {
        move();
        checkEdgeCollision();
        checkUnitCollision();
        //onEdgeCollsion();
        display();
    }

    void move() {
        position.add(velocity);
    }

    void checkUnitCollision() {
        if (Utility.contains(canHit, "Enemy")){
            for (int i = 0; i < DG.enemies.size(); i++){
                Enemy e = DG.enemies.get(i);
                if (PVector.dist(position, e.position) < radius + e.radius){
                    onEnemyCollision(e);
                    break;
                }
            }
        }

        if (Utility.contains(canHit, "Player")){
            if (PVector.dist(position, DG.p.position) < radius + DG.p.radius){
                onPlayerCollision();
            }
        }
    }

    void onEnemyCollision(Enemy e){
        e.takeDamage(dmg);
        hit = true;
    }

    void onPlayerCollision(){

    }

    void checkEdgeCollision() { 
        if (position.x + radius >= DG.width || position.x - radius <= 0) {
            hit = true;
        }
        if (position.y + radius >= DG.height || position.y - radius <= 0) {
            hit = true;
        } 
    }

    void display(){
        // draws and colors the projectile 
        DG.fill(color);
        Utility.circle(position, radius);        
    }

}
