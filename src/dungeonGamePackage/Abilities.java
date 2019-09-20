package dungeonGamePackage;

//A collection of the ability anonymous classes 
import static dungeonGamePackage.DungeonGame.DG;

import processing.core.PVector;

class Abilities{
    static Ability chainLightningShot = new Ability(1.5f, 10){
        @Override
        void use(){
            DG.projectiles.add(new Projectile(PVector.add(DG.p.position, DG.p.barrelLongSide), PVector.mult(DG.p.lookDirection, 10f), (int)(scaling*DG.p.dmg),
             new String[]{"Enemy"}, 10, DG.color(30)){
                int numChains = 3;
                int chainCount = 0;
                float range = 1000f;
                
                void chain(Enemy e){
                    DG.println("chain");
                    DG.println(range);
                    e.hit = true;
                    e.takeDamage(dmg);
                    float shortestDist = Float.POSITIVE_INFINITY;
                    Enemy closestEnemy = null;
                    for (int i = 0; i < DG.zone.enemies.size(); i++){
                        DG.println(i);
                        Enemy other = DG.zone.enemies.get(i);
                        if (other.hit) continue;
                        float dist = PVector.dist(e.position, other.position);
                        DG.println(dist);
                        if (dist < shortestDist){
                            shortestDist = dist;
                            closestEnemy = other;
                        }    
                    }
                    if (shortestDist > range || closestEnemy == null || chainCount == numChains){
                        DG.println(shortestDist, closestEnemy, chainCount);
                        hit = true;
                        return;
                    } else{
                        chainCount++;
                        drawLightning(e.position, closestEnemy.position);
                        chain(closestEnemy);
                    }
                }

                void generateLightning(PVector position1, PVector position2){
                    PVector direction = PVector.sub(position2, position1).normalize();
                    float len = PVector.dist(position1, position2);
                    int numMemes = DG.floor(DG.random(8, 15));
                    
                    PVector[] memePoints = new PVector[numMemes];
                    memePoints[0] = position1;
                    memePoints[numMemes-1] = position2;
                    
                    int d = 1;
                    for (int i = 0; i < numMemes; i++){
                        PVector linePoint = PVector.add(position1, PVector.mult(direction, len/numMemes*(i+1)));
                        PVector perpendicularBoi = new PVector(-direction.y, direction.x).mult(d*DG.random(1, 10));
                        memePoints[i] = PVector.add(linePoint, perpendicularBoi);
                        d *= -1;
                    }
                }

                void drawLightning(){
                    
                    
                    DG.strokeWeight(3);
                    DG.stroke(0, 128, 255);
                    for (int i = 0; i < memePoints.length-1; i++){
                        DG.line(memePoints[i].x, memePoints[i].y, memePoints[i+1].x, memePoints[i+1].y);
                    }
                  }

                @Override
                void onEnemyCollision(Enemy e){
                    chain(e);
                }

             });
        }
    };


}