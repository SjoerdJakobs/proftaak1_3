package MainPackage.Simulation.Npc;

import OOFramework.FrameworkProgram;

import java.awt.image.BufferedImage;

public class Teacher extends Npc {
    private BufferedImage[] mySprites = new BufferedImage[12];



    protected Teacher(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);

    }

    private void initializeSprites(){

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                mySprites[i + j * 4] = super.spriteSheet[48 + 12 * j + i];
            }
        }
    }
}
