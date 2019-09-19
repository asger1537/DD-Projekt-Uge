package dungeonGamePackage;

import java.util.ArrayList;

class Screen {
    ArrayList<Button> buttons;
    
    Screen(ArrayList<Button> buttons){
        this.buttons = buttons;
    }
    
    void update(){
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).update();
        }
    }


}