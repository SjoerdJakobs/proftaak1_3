package MainPackage.Simulation.Npc;

import OOFramework.FrameworkProgram;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.util.Random;


public class Student extends Npc {
    //private BufferedImage[] mySprites = new BufferedImage[12];

    public Student(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D cords) {
        super(frameworkProgram, graphics2D, cords);
    }

    @Override
    protected void initializeSprites() {
        Random random = new Random();
        int spriteSeed = random.nextInt(5);
        int spriteOffset;
        switch (spriteSeed) {
            case 0:
                spriteOffset = 0;
                break;
            case 1:
                spriteOffset = 3;
                break;
            case 2:
                spriteOffset = 6;
                break;
            case 3:
                spriteOffset = 51;
                break;
            case 5:
                spriteOffset = 54;
                break;

            default:
                spriteOffset = 0;
                break;
        }


        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                super.mySprites[i + j * 3] = super.spriteSheet[12 * j + i + spriteOffset];
            }
        }


    }


}
