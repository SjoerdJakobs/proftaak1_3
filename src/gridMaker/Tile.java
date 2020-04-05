package gridMaker;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Tile {


    private Rectangle2D.Double gridRect;
    private Point2D gridPos;

    private int tileWidth = 16;
    private int tileHeight = 16;

    private HashMap<String,Direction> directions;

    private boolean isWall = false;
    private boolean hasWalAbove = false;
    private boolean hasWalBelow = false;
    private boolean hasWalToTheRight = false;
    private boolean hasWalToTheLeft = false;

    private boolean hasBeenSet = false;

    private boolean isDestination;



    public Tile(int x, int y){

        directions = new HashMap<>();
        this.gridPos = new Point2D.Double(x, y);
        gridRect = new Rectangle2D.Double(this.gridPos.getX(), this.gridPos.getY(), tileWidth, tileHeight);

    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isHasWalAbove() {
        return hasWalAbove;
    }

    public void setHasWalAbove(boolean hasWalAbove) {
        this.hasWalAbove = hasWalAbove;
    }

    public boolean isHasWalBelow() {
        return hasWalBelow;
    }

    public void setHasWalBelow(boolean hasWalBelow) {
        this.hasWalBelow = hasWalBelow;
    }

    public boolean isHasWalToTheRight() {
        return hasWalToTheRight;
    }

    public void setHasWalToTheRight(boolean hasWalToTheRight) {
        this.hasWalToTheRight = hasWalToTheRight;
    }

    public boolean isHasWalToTheLeft() {
        return hasWalToTheLeft;
    }

    public void setHasWalToTheLeft(boolean hasWalToTheLeft) {
        this.hasWalToTheLeft = hasWalToTheLeft;
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
