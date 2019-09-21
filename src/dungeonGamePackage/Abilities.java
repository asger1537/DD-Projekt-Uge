package dungeonGamePackage;

//A collection of the ability anonymous classes 
import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PVector;

class Abilities {
    static Ability piercingProjectile = new Ability(0.05f,10, null){
        @Override
        void use() {
            DG.projectiles.add(new Projectile(PVector.add(DG.p.position, DG.p.barrelLongSide),
                    PVector.mult(DG.p.lookDirection, 10f), (int) (scaling * DG.p.dmg), new String[] { "Enemy" }, 40,
                    DG.color(0,0,255)) {

                HashSet<Enemy> alreadyhit = new HashSet<Enemy>();

                @Override
                void onEnemyCollision(Enemy e) {
                    if(alreadyhit.contains(e)) return;
                    super.onEnemyCollision(e);
                    e.takeDamage(4 * DG.p.dmg / (alreadyhit.size()+1));
                    hit = false;
                    alreadyhit.add(e);
                }

                @Override
                void display() {
                    DG.imageMode(DG.CENTER);
                    DG.image(DG.frostBoltimage, position.x, position.y);
                }
            });
            cdCurrent = cd;
        }
    };

    static Ability standardShot = new Ability(1, 0.5f, null) {
        @Override
        void use() {
            if (DG.p.atkcdCurrent == 0) {
                DG.projectiles.add(new Projectile(PVector.add(DG.p.position, DG.p.barrelLongSide),
                        PVector.mult(DG.p.lookDirection, 10), DG.p.dmg, new String[] { "Enemy" }, 5, DG.color(30)));
                cdCurrent = cd;
            }
        }
    };

    static Ability chainLightningShot = new Ability(1.5f, 10, null) {
        @Override
        void use() {
            DG.projectiles.add(new Projectile(PVector.add(DG.p.position, DG.p.barrelLongSide),
                    PVector.mult(DG.p.lookDirection, 10f), (int) (scaling * DG.p.dmg), new String[] { "Enemy" }, 10,
                    DG.color(30)) {
                int numChains = 3;
                int chainCount = 0;
                float range = 200f;

                void chain(Enemy e) {
                    e.hit = true;
                    e.takeDamage(dmg);
                    float shortestDist = Float.POSITIVE_INFINITY;
                    Enemy closestEnemy = null;
                    for (int i = 0; i < DG.zone.enemies.size(); i++) {
                        Enemy other = DG.zone.enemies.get(i);
                        if (other.hit)
                            continue;
                        float dist = PVector.dist(e.position, other.position);
                        if (dist < shortestDist) {
                            shortestDist = dist;
                            closestEnemy = other;
                        }
                    }
                    if (shortestDist > range || closestEnemy == null || chainCount == numChains) {
                        hit = true;
                        return;
                    } else {
                        chainCount++;
                        PVector[] lightningPoints = generateLightning(e.position, closestEnemy.position.copy());
                        DG.particles.add(new Particle(30) {

                            @Override
                            void display() {
                                DG.strokeWeight(3);
                                DG.stroke(0, 128, 255);
                                for (int i = 0; i < lightningPoints.length - 1; i++) {
                                    DG.line(lightningPoints[i].x, lightningPoints[i].y, lightningPoints[i + 1].x,
                                            lightningPoints[i + 1].y);
                                }
                                DG.stroke(0);
                            }
                        });
                        chain(closestEnemy);
                    }
                }

                PVector[] generateLightning(PVector position1, PVector position2) {
                    PVector direction = PVector.sub(position2, position1).normalize();
                    float len = PVector.dist(position1, position2);
                    int numCracks = DG.floor(DG.random(8, 15));

                    PVector[] lightningPoints = new PVector[numCracks];
                    lightningPoints[0] = position1;
                    lightningPoints[numCracks - 1] = position2;

                    int d = 1;
                    for (int i = 0; i < numCracks - 1; i++) {
                        PVector linePoint = PVector.add(position1, PVector.mult(direction, len / numCracks * (i + 1)));
                        PVector perpendicularBoi = new PVector(-direction.y, direction.x).mult(d * DG.random(1, 10));
                        lightningPoints[i] = PVector.add(linePoint, perpendicularBoi);
                        d *= -1;
                    }
                    return lightningPoints;
                }

                @Override
                void onEnemyCollision(Enemy e) {
                    chain(e);
                }

            });

            cdCurrent = cd;
        }

    };

}