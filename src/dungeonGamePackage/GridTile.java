package dungeonGamePackage;

import processing.core.PVector;

class GridTile {
    PVector position;
    int i, j;
    boolean obstruction;

    GridTile(PVector position, int i, int j, boolean obstruction){
        this.position = position;
        this.i = i;
        this.j = j;
        this.obstruction = obstruction;
    }

}