package MainPackage;

import OOFramework.FrameworkProgram;
import javafx.stage.Stage;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


public class Program extends FrameworkProgram
{
    sAgenda agenda;
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init()
    {
        super.Init();

        agenda = new sAgenda(this);

    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();
        //menu switch?

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }
}

