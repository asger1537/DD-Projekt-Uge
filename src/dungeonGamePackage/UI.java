package dungeonGamePackage;

import static dungeonGamePackage.DungeonGame.DG;

import java.util.ArrayList;

class UI {

    static float abilityBarX, abilityBarY, abilityBarLength, abilityBarHeight, abilityBarTextHeight;
    static float expBarX, expBarY, expBarLength, expBarHeight;
    static String[] keybinds;

    static Screen startMenu, gameScreen, deathScreen;

    static void initializeVariables() {
        keybinds = new String[] { "r clk", "q", "w", "e", "r" };

        // AbilityBar variables
        abilityBarLength = DG.width * 0.25f;
        abilityBarX = DG.width / 2f - abilityBarLength / 2f;// from center
        abilityBarHeight = abilityBarLength / 5f;
        abilityBarTextHeight = abilityBarHeight / 4f;
        abilityBarY = DG.height - abilityBarTextHeight - abilityBarHeight;

        // Expbar variables
        expBarLength = abilityBarLength;
        expBarHeight = abilityBarHeight / 5f;
        expBarX = abilityBarX;
        expBarY = abilityBarY - expBarHeight;

        gameScreen = new Screen(new ArrayList<Button>()) {
            @Override
            void display() {
                DG.pushMatrix();
                DG.translate((int) (DG.width / 2f - DG.p.position.x), (int) (DG.height / 2f - DG.p.position.y));
                DG.zone.update();
                DG.p.update();
                for (int i = 0; i < DG.projectiles.size(); i++) {
                    DG.projectiles.get(i).update();
                    if (DG.projectiles.get(i).hit) {
                        DG.projectiles.remove(i);
                    }
                }
                for (int i = 0; i < DG.particles.size(); i++){
                    DG.particles.get(i).update();
                }

                DG.popMatrix();

                showAbilityBar();
                showExpBar();
            }
        };

        ClickInterface startButtonOnClick = () -> DG.currentScreen = gameScreen;
        ArrayList<Button> startMenuButtons = new ArrayList<Button>();
        startMenuButtons.add(new TextButton(DG.width / 2, DG.height / 2, 100f, 25, DG.color(128), "START", DG.color(0),
                18, startButtonOnClick));
        startMenu = new Screen(startMenuButtons);

        ClickInterface restartButtonOnClick = () -> DG.setup();
        ArrayList<Button> deathScreenButtons = new ArrayList<Button>();
        deathScreenButtons.add(new TextButton(DG.width / 2, DG.height / 2, 200f, 25, DG.color(128), "GO START MENU",
                DG.color(0), 18, restartButtonOnClick));
        deathScreen = new Screen(deathScreenButtons) {
            @Override
            void display() {
                DG.background(128);
                DG.textAlign(DG.CENTER);
                DG.textSize(50);
                DG.fill(234, 0, 0);
                DG.text("YOU DIED", DG.width / 2f, DG.height * 0.3f);
            }
        };
    }

    static void showAbilityBar() {
        DG.println(abilityBarHeight);
        DG.rectMode(DG.CORNER);
        int keybindIdx = 0;
        for (float x = abilityBarX; x < abilityBarX + abilityBarLength; x += abilityBarLength / 5f) {
            DG.fill(50, 128);
            DG.rect(x, abilityBarY, abilityBarHeight, abilityBarHeight);
            DG.rect(x, abilityBarY + abilityBarHeight, abilityBarHeight, abilityBarTextHeight);
            //displaying the icon of the ability in that slot
            //DG.image(DG.p.abilities[keybindIdx].icon, x, abilityBarY);

            //displaying cooldown
            DG.fill(112, 146, 190, 128);
            Ability a = DG.p.abilities[keybindIdx];
            if (a != null){
                DG.rect(x, abilityBarY + abilityBarHeight, abilityBarHeight, -abilityBarHeight*a.cdCurrent/a.cd);
            }
            String keybind = keybinds[keybindIdx];
            DG.textSize(25);
            DG.textAlign(DG.CENTER);
            DG.fill(10);
            DG.text(keybind, x + abilityBarHeight / 2, DG.height - abilityBarTextHeight / 10f);
            keybindIdx++;
        }
    }

    static void showExpBar() {
        String expBarContent = DG.p.exp + "/" + DG.p.expLevelUp;
        DG.fill(0);
        DG.text("lvl" + DG.p.lvl, expBarX + expBarLength / 2, expBarY);
        DG.rectMode(DG.CORNER);
        DG.fill(50, 128);
        DG.rect(expBarX, expBarY, expBarLength, expBarHeight);// drawing the bar
        DG.fill(125, 0, 115);
        DG.rect(expBarX, expBarY, expBarLength * DG.p.exp / DG.p.expLevelUp, expBarHeight);
        // dividing the expbar into 10 parts
        for (float x = expBarX; x < expBarX + expBarLength; x += expBarLength / 10f) {
            DG.line(x, expBarY, x, expBarY + expBarHeight);
        }
        DG.fill(0);
        DG.text(expBarContent, expBarX + expBarLength / 2, (float) (expBarY + expBarHeight * 0.8));
    }

    @FunctionalInterface
    interface ClickInterface {
        void use();
    }

}