package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Main extends Application {
    ResizableCanvas canvas;
    public Point2D position = new Point2D.Double(100,100);

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(new Menu("Agenda"),new Menu("Simulation"));
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        mainPane.setTop(menuBar);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    public void draw(FXGraphics2D g2d) {
        g2d.setBackground(Color.white);
        g2d.clearRect(0,0,1920,1080);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(new Rectangle2D.Double(position.getX()-50, position.getY()-50, 100, 100));
        int stepsize = (int)canvas.getWidth()/20;
        for(int i =0; i<20; i++){
            g2d.drawLine((int)stepsize*i,0,(int)stepsize*i,(int)canvas.getHeight());
        }
    }

    private void mousePressed(MouseEvent e) {
    }

    private void mouseReleased(MouseEvent e) {

    }

    private void mouseDragged(MouseEvent e) {
        position = new Point2D.Double(e.getX(), e.getY());
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }



    public static void main(String[] args) {
        launch(args);
    }
}
