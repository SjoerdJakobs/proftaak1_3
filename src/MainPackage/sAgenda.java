package MainPackage;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static OOFramework.Modules.CONSTANTS.CANVAS_HEIGHT;
import static OOFramework.Modules.CONSTANTS.CANVAS_WIDTH;

public class sAgenda extends StandardObject
{
    private Graphics2D graphics2D;
    private Renderable hourBlock;
    private Canvas canvas;

    protected sAgenda(FrameworkProgram frameworkProgram)
    {
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);

        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.canvas = frameworkProgram.getCanvas();

        //create a simple rectangle, just repeat this code for more
        this.hourBlock = new Renderable(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(400, 400), 1 * (float) Math.PI, 1);
    }

    @Override
    protected void InputLoop(double deltaTime)
    {
        super.InputLoop(deltaTime);
        //lets put stuff like adding data for a lesson here
    }

    @Override
    protected void MainLoop(double deltaTime)
    {
        super.MainLoop(deltaTime);
        //do
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        //lets draw all stuff here

        //with the code here we draw a red square each frame, every frame already gets cleared at the beginning of  the rendering.
        graphics2D.setColor(Color.red);
        graphics2D.draw(hourBlock.getTransformedShape());
        graphics2D.fill(hourBlock.getTransformedShape());

        //renderable has a draw function as well, you can choose if you want to draw it here or there.
    }
}