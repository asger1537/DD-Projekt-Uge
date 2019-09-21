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
	ArrayList<Particle> particles;
	PImage portalImg, floorTileImg, meleeEnemyImg, standardShotIcon, chainLightningShotIcon, frostBoltimage;

	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		fullScreen();
		DG = this;
	}

	public void setup() {
		UI.initializeVariables();
		portalImg = loadImage("portal.png");
		floorTileImg = loadImage("floorTile.png");
		meleeEnemyImg = loadImage("meleeEnemy.png");
		standardShotIcon = loadImage("standardShotIcon.png");
		chainLightningShotIcon = loadImage("chainLightningShotIcon.png");
		frostBoltimage = loadImage("frostboltpng.png");
		
		currentScreen = UI.startMenu;
		zone = new Zone(2000, 2000, 1);
		p = new Player();
		p.learnAbility(Abilities.standardShot, 0);
		p.learnAbility(Abilities.chainLightningShot, 1);
		p.learnAbility(Abilities.piercingProjectile, 2);
		//p.learnAbility(Abilities., idx);
		UI.setAbilityButtons();
		zone.spawnZoneEnemies();
		projectiles = new ArrayList<Projectile>();		
		particles = new ArrayList<Particle>();
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
