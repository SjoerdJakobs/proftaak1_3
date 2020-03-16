package gridMaker;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class Tile {


    private Rectangle2D.Double gridRect;
    private Point2D gridPos;

    private int tileWidth = 16;
    private int tileHeight = 16;

    private HashMap<String,Direction> directions;

    private boolean isWall = false;
    private boolean hasTopWall = false;
    private boolean hasBollomWall = false;
    private boolean hasRightWall = false;
    private boolean hasleftWall = false;

    private boolean hasBeenSet = false;

    private boolean isDestination;



    public Tile(int x, int y){

        directions = new HashMap<>();
        this.gridPos = new Point2D.Double(x, y);
        gridRect = new Rectangle2D.Double(this.gridPos.getX(), this.gridPos.getY(), tileWidth, tileHeight);

    }

//    public void draw(FXGraphics2D graphics){
//
//            graphics.setColor(this.color);
//            graphics.draw(this.gridRect);
//
//            graphics.setColor(Color.black);
//    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isHasTopWall() {
        return hasTopWall;
    }

    public void setHasTopWall(boolean hasTopWall) {
        this.hasTopWall = hasTopWall;
    }

    public boolean isHasBollomWall() {
        return hasBollomWall;
    }

    public void setHasBollomWall(boolean hasBollomWall) {
        this.hasBollomWall = hasBollomWall;
    }

    public boolean isHasRightWall() {
        return hasRightWall;
    }

    public void setHasRightWall(boolean hasRightWall) {
        this.hasRightWall = hasRightWall;
    }

    public boolean isHasleftWall() {
        return hasleftWall;
    }

    public void setHasleftWall(boolean hasleftWall) {
        this.hasleftWall = hasleftWall;
    }

    public boolean isHasBeenSet() {
        return hasBeenSet;
    }

    public void setHasBeenSet(boolean hasBeenSet) {
        this.hasBeenSet = hasBeenSet;
    }

    public boolean isDestination() {
        return isDestination;
    }

    public void setDestination(boolean destination) {
        isDestination = destination;
    }

    public HashMap<String, Direction> getDirections() {
        return directions;
    }

    public void setDirections(HashMap<String, Direction> directions) {
        this.directions = directions;
    }




    public Rectangle2D.Double getGridRect() {
        return gridRect;
    }

    public void setGridRect(Rectangle2D.Double gridRect) {
        this.gridRect = gridRect;
    }

    public Point2D getGridPos() {
        return gridPos;
    }

    public void setGridPos(Point2D gridPos) {
        this.gridPos = gridPos;
    }
}
