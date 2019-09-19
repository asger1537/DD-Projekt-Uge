package dungeonGamePackage;

import java.util.ArrayList;

class Screen {
    ArrayList<UIelement> UIelements;
    
    Screen(ArrayList<UIelement> UIelements){
        this.UIelements = UIelements;
    }
    
    void update(){
        for (int i = 0; i < UIelements.size(); i++){
            UIelements.get(i).update();
        }
    }


}