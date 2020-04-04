package MainPackage.Simulation.Npc;

import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Teacher extends Npc {




    public Teacher(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D, Point2D position, StudentData studentData) {
        super(frameworkProgram,graphics2D, position, studentData);

    }

    @Override
    protected void initializeSprites(){ //generates the sprites necessarry for allowing walkcylces


        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {


                System.out.println(i+j *3);
                super.mySprites[i + j *3 ] = super.spriteSheet[48 + 12 * j + i];
            }
        }
    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);

    }
}
