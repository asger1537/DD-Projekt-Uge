package dungeonGamePackage;

import java.util.ArrayList;

import dungeonGamePackage.UI.ClickInterface;
import processing.core.PApplet;
import processing.core.PVector;

public class DungeonGame extends PApplet {
	public static DungeonGame DG;

	// global variables
	Player p;
	Zone zone;
	ArrayList<Projectile> projectiles;

	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		// size(800, 800);
		fullScreen();
		DG = this;
	}

	public void setup() {
		p = new Player();
		zone = new Zone();
		projectiles = new ArrayList<Projectile>();
		zone.spawnZoneEnemies();
		UI.initializeVariables();
	}

	public void draw() {
		background(255);
		p.update();
		zone.update();
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).hit) {
				projectiles.remove(i);
			}
		}

		UI.showAbilityBar();
		UI.showExpBar();
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
