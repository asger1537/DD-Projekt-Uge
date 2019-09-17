package dungeonGamePackage;

import processing.core.PApplet;

public class DungeonGame extends PApplet{
	public static DungeonGame DG;
	
	//global variables
	Player p;
	int currentZone;
	Projectile proj;
	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		size(800, 800);
		DG = this;
	}

	public void setup() {
		p = new Player();
		currentZone = 0;
	}

	public void draw() {
		proj.update();
	}

	@Override
	public void mousePressed(){
		Input.mousePressed();
	}

	@Override
	public void keyPressed(){
		Input.keyPressed();
	}

}
