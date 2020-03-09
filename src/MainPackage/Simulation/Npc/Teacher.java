package MainPackage.Simulation.Npc;

import OOFramework.FrameworkProgram;
import org.jfree.fx.FXGraphics2D;

import java.awt.image.BufferedImage;

public class Teacher extends Npc {
    //private BufferedImage[] mySprites = new BufferedImage[12];




    protected Teacher(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D) {
        super(frameworkProgram,graphics2D);

    }
    @Override
    protected void initializeSprites(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                super.mySprites[i + j * 4] = super.spriteSheet[48 + 12 * j + i];
            }
        }
    }

}
