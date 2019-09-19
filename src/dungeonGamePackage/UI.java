package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

class UI{

    static float abilityBarX, abilityBarY, abilityBarLength, abilityBarHeight, abilityBarTextHeight;
    static float expBarX, expBarY, expBarLength, expBarHeight;
    static String[] keybinds;

    static Screen startMenu, gameScreen;


    static void initializeVariables(){
        keybinds = new String[]{"r clk", "q", "w", "e", "r"};

        //AbilityBar variables
        abilityBarLength = DG.width*0.25f;
        abilityBarX = DG.width/2f-abilityBarLength/2f;//from center
        abilityBarHeight = abilityBarLength/5f;
        abilityBarTextHeight = abilityBarHeight/4f;
        abilityBarY = DG.height-abilityBarTextHeight-abilityBarHeight;

        //Expbar variables
        expBarLength = abilityBarLength;
        expBarHeight = abilityBarHeight/5f;
        expBarX = abilityBarX;
        expBarY = abilityBarY - expBarHeight;


        gameScreen = new Screen(new ArrayList<Button>()){
            @Override 
            void update(){
                DG.pushMatrix();
                DG.translate(DG.width/2f-DG.p.position.x, DG.height/2f-DG.p.position.y);
                DG.zone.update();
                DG.p.update();
                for (int i = 0; i < DG.projectiles.size(); i++) {
                    DG.projectiles.get(i).update();
                    if (DG.projectiles.get(i).hit) {
                        DG.projectiles.remove(i);
                    }
                }
                DG.popMatrix();

                showAbilityBar();
                showExpBar();
            }
        };

        ArrayList<Button> startMenuElements = new ArrayList<Button>();
        
        ClickInterface startButtonOnClick = () -> DG.currentScreen = gameScreen; 
        startMenuElements.add(new TextButton(DG.width/2, DG.height/2, 100f, 25, DG.color(128), "START", DG.color(0), 18, startButtonOnClick));
        startMenu = new Screen(startMenuElements);
    }


    static void showAbilityBar(){
        DG.rectMode(DG.CORNER);
        int keybindIdx = 0;
        for (float x = abilityBarX; x < abilityBarX + abilityBarLength; x += abilityBarLength/5f){
            DG.noFill();
            DG.rect(x, abilityBarY, abilityBarHeight, abilityBarHeight);
            DG.rect(x, abilityBarY + abilityBarHeight, abilityBarHeight, abilityBarTextHeight);
            String keybind = keybinds[keybindIdx];
            DG.textSize(25);
            DG.textAlign(DG.CENTER);
            DG.fill(50);
            DG.text(keybind, x+abilityBarHeight/2, DG.height-abilityBarTextHeight/10f);
            keybindIdx++;
            //To do - add keybind text
        }
    }

    static void showExpBar(){
        DG.rectMode(DG.CORNER);
        DG.noFill();
        DG.rect(expBarX, expBarY, expBarLength, expBarHeight);//drawing the bar
        DG.fill(125, 0, 115);
        DG.rect(expBarX, expBarY, expBarLength*DG.p.exp/DG.p.expLevelUp, expBarHeight);    
        
        //dividing the expbar into 10 parts
        for (float x = expBarX; x < expBarX+expBarLength; x += expBarLength/10f){
            DG.line(x, expBarY, x, expBarY + expBarHeight);
        }
    }

    @FunctionalInterface
    interface ClickInterface{ 
        void use(); 
    }

    

}