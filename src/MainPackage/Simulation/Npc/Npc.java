package MainPackage.Simulation.Npc;

import MainPackage.Simulation.Logic.Direction;
import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Npc extends StandardObject {
    protected LogicalTile currentTile;
    protected Point2D cords;
    protected BufferedImage[] spriteSheet = SPRITESHEET.Sprites;
    protected BufferedImage[] mySprites = new BufferedImage[12];
    protected FXGraphics2D graphics2D;
    protected int walkcyle = 0;
    protected double timePassed = 0;
    protected double cycleTime = 100;


    protected Npc(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D) {
        super(frameworkProgram);
        initializeSprites();
        this.graphics2D = graphics2D;
    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {
        testCyle(deltaTime);

    }

    @Override
    protected void RenderLoop(double deltaTime) {


    }


    protected void initializeSprites() {
    }


    protected BufferedImage getImageToDraw(Direction direction, boolean isWalking) {

        int spriteLayer = 0;
        switch (direction) {


            case UP:
                spriteLayer = 3;
                break;
            case DOWN:
                spriteLayer = 0;
                break;
            case LEFT:
                spriteLayer = 1;
                break;
            case RIGHT:
                spriteLayer = 2;
                break;
        }


        if (!isWalking) {
            return mySprites[spriteLayer * 3 + 1];
        } else {
            switch (walkcyle) {
                case 0:
                case 4:
                    return mySprites[spriteLayer * 3 + 1];
                case 1:
                    return mySprites[spriteLayer * 3];

                case 2:
                    return mySprites[spriteLayer * 3 + 2];

                default:
                    return mySprites[spriteLayer * 3 + 1];


            }
        }
    }

    protected void testCyle(double deltatime) {
        timePassed += deltatime;
        if (timePassed > cycleTime) {
            timePassed = 0;
            walkcyle++;
            if (walkcyle == 4) {
                walkcyle = 0;
            }


        }
    }


}

