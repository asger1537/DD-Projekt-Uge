package dungeonGamePackage;

import processing.core.PApplet;

public class DungeonGame extends PApplet{
	public static DungeonGame DG;
	public static void main(String[] args) {
		PApplet.main("dungeonGamePackage.DungeonGame");
	}

	public void settings() {
		size(800, 800);
		DG = this;
	}

	public void setup() {

	}

	public void draw() {
  
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
