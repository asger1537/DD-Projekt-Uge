package dungeonGamePackage;

import processing.core.PImage;
import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;


class GridTile {
    PVector position;
    int i, j;
    boolean obstruction;
    PImage img;

    GridTile(PVector position, int i, int j, boolean obstruction, PImage img){
        this.position = position;
        this.i = i;
        this.j = j;
        this.obstruction = obstruction;
        this.img = img;
    }

    void display(){
        DG.image(img, position.x, position.y);
    }

    float getDistance(GridTile other){
        return (float)Math.sqrt((i-other.i)*(i-other.i)+(j-other.j)*(j-other.j));
    }

}