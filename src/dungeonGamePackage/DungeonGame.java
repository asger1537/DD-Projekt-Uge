package dungeonGamePackage;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class DungeonGame extends PApplet {
	public static DungeonGame DG;

	// global variables
	Player p;
	int currentZone;
	ArrayList<Enemy> enemies;
	ArrayList<Projectile> projectiles;
	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		size(800, 800);
		// fullScreen();
		DG = this;
	}

	public void setup() {
		p = new Player();
		currentZone = 0;
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();

		Utility.spawnEnemyCluster(new PVector(width / 2, height / 2));
	}

	public void draw() {
		println();
		background(255);
		p.update();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			if (enemies.get(i).dead) {
				enemies.get(i).onDeath();
				enemies.remove(i);
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).hit) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void mousePressed() {
		Input.mousePressed();
	}

	@Override
	public void keyPressed() {
		Input.keyPressed();
	}

}
