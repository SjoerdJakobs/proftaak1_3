package MainPackage.Simulation.Npc;

import OOFramework.FrameworkProgram;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Teacher extends Npc {




    public Teacher(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D cords) {
        super(frameworkProgram,graphics2D, cords);

    }
    @Override
    protected void initializeSprites(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {


                System.out.println(i+j *3);
                super.mySprites[i + j *3 ] = super.spriteSheet[48 + 12 * j + i];
            }
        }
    }

}
