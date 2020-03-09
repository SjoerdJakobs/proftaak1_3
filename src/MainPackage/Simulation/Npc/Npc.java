package MainPackage.Simulation.Npc;

import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Npc extends StandardObject {
protected LogicalTile currentTile;
protected Point2D position;
protected FXGraphics2D graphics2D;
protected BufferedImage[] spriteSheet = SPRITESHEET.Sprites;
protected  BufferedImage[] mySprites = new BufferedImage[12];
protected FXGraphics2D graphics2D;


    protected Npc(FrameworkProgram frameworkProgram, FXGraphics2D graphics2D) {
        super(frameworkProgram);
        this.graphics2D = graphics2D;
        initializeSprites();
        this.graphics2D = graphics2D;
    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {

    }

    @Override
    protected void RenderLoop(double deltaTime){



    }


    protected void initializeSprites(){
    }


    




}

