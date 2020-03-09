package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Main extends Application {
    private ResizableCanvas canvas;
    private VBox vBox;
    public Point2D position = new Point2D.Double(100, 100);

    private int rows = 8;
    private int columns = 12;
    private int fontSize=15;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(new Menu("Agenda"), new Menu("Simulation"));

        BorderPane borderPane = new BorderPane();

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        vBox = new VBox();
        borderPane.setRight(canvas);
        borderPane.setLeft(vBox);

        vBox.setSpacing((1080-rows*fontSize)/rows);

        String[] rowLabels = {"unos","dos","tres", "quadros", "vijf", "zes", "seven", "eight", "nein", "tin"};

        for (int i = 0; i < rowLabels.length && i < this.rows; i++) {
            Label label = new Label(rowLabels[i]);
            label.setFont(new Font(fontSize));
            this.vBox.getChildren().add(new Label(rowLabels[i]));
        }


        mainPane.setCenter(borderPane);
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
        g2d.clearRect(0, 0, 1920, 1080);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(new Rectangle2D.Double(position.getX() - 50, position.getY() - 50, 100, 100));
        int vertStepsize = (int) ((canvas.getWidth() - vBox.getWidth()) / this.columns);
        int horStepsize = (int) canvas.getHeight() / this.rows;

        //Horizontal lines
        for (int i = 0; i < this.rows; i++) {
            g2d.drawLine(0, horStepsize * i, (int) canvas.getWidth(), horStepsize * i);
        }

        //Vertical Lines
        for (int i = 0; i < this.columns; i++) {
            g2d.drawLine(vertStepsize * i, 0, vertStepsize * i, (int) canvas.getHeight());
            g2d.drawString((i + 8) + ":00", i * vertStepsize, 10);
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


    //public static void main(String[] args) {
    //    launch(args);
    //
    //}


}


