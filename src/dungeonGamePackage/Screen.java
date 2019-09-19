package dungeonGamePackage;

import java.util.ArrayList;
import static dungeonGamePackage.DungeonGame.DG;

class Screen {
    ArrayList<Button> buttons;
    
    Screen(ArrayList<Button> buttons){
        this.buttons = buttons;
    }
    
    void update(){
        display();
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).update();
        }
    }

    void display(){
        DG.background(128);    
    }


}