package dungeonGamePackage;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class DungeonGame extends PApplet {
	public static DungeonGame DG;

	// global variables
	Player p;
	Zone zone;
	Screen currentScreen;
	ArrayList<Projectile> projectiles;
	PImage portalImg, floorTileImg, meleeEnemyImg;

	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		fullScreen();
		DG = this;
	}

	public void setup() {
		UI.initializeVariables();
		currentScreen = UI.startMenu;
		p = new Player();
		zone = new Zone(2000f, 2000f);
		projectiles = new ArrayList<Projectile>();
		zone.spawnZoneEnemies();
		portalImg = loadImage("portal.png");
		floorTileImg = loadImage("floorTile.png");
		meleeEnemyImg = loadImage("meleeEnemy.png");
	}

	public void draw() {
		currentScreen.update();
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
