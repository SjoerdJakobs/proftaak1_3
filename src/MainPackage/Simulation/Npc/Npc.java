package MainPackage.Simulation.Npc;

import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Npc extends StandardObject {
protected LogicalTile currentTile;
protected Point2D cords;
protected BufferedImage[] spriteSheet = SPRITESHEET.Sprites;


    protected Npc(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);
        initializeSprites();

    }


    @Override
    protected void InputLoop(double deltaTime) {

    }

    @Override
    protected void MainLoop(double deltaTime) {

    }

    @Override
    protected void RenderLoop(double deltaTime){}


    protected void initializeSprites(){

    }



}

