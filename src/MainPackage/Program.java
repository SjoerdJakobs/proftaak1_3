package MainPackage;

import OOFramework.ExampleClasses.ExampleStudent;
import OOFramework.FrameworkProgram;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

import static javafx.application.Application.launch;

public class Program extends FrameworkProgram
{
    //student variable for example
    ExampleStudent Student;
    ExampleStudent Student2;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    public void draw(FXGraphics2D g2d) {
        super.draw(g2d);

    }

    @Override
    protected void Start() {
        super.Start();

        // create new student for example
        Student = new ExampleStudent(this, 6 );
        Student2 = new ExampleStudent(this, 9 );
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
