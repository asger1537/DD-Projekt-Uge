package dungeonGamePackage;

import processing.core.PVector;
import static dungeonGamePackage.DungeonGame.DG;

class Utility {

    static void circle(PVector position, float radius){
        DG.ellipse(position.x, position.y, radius*2, radius*2);
    }

    static int sign(float x){
        if (x < 0) return -1;
        if (x > 0) return 1;
        return 0;
    }
}