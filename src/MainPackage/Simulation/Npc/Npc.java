package MainPackage.Simulation.Npc;

import MainPackage.Simulation.Logic.LogicalTile;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

import java.awt.geom.Point2D;

public class Npc extends StandardObject {
LogicalTile currentTile;
Point2D cords;


    protected Npc(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);
    }


    @Override
    protected void InputLoop(double deltaTime) {
        
    }

    @Override
    protected void MainLoop(double deltaTime) {

    }

    @Override
    protected void RenderLoop(double deltaTime){}






}

