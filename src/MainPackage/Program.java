package MainPackage;

import OOFramework.FrameworkProgram;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Program extends FrameworkProgram
{
    sAgenda agenda;
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    public void draw(FXGraphics2D g2d)
    {
        super.draw(g2d);

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

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }
}
