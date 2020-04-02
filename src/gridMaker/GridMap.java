package gridMaker;

import TiledParser.TileMapJSONParser;
import org.jfree.fx.FXGraphics2D;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static OOFramework.Modules.CONSTANTS.*;

public class GridMap {
    private Tile[][] tiles;
    private JsonArray collisonLayer;

    private TileMapJSONParser tileMapJSONParser;
    private BufferedImage[] sprites;
    private int mapWidth;
    private int mapHeight;
    private int tileWidth;
    private int tileHeight;

    private JsonArray objectLayer;


    public GridMap(TileMapJSONParser tileMapJSONParser, BufferedImage[] sprites) {
        this.tileMapJSONParser = tileMapJSONParser;
        this.sprites = sprites;

        this.collisonLayer = tileMapJSONParser.getCollisionData();
        this.objectLayer = tileMapJSONParser.getObjectLayer();

        this.mapWidth = tileMapJSONParser.getWidth();
        this.mapHeight = tileMapJSONParser.getHeight();

        this.tileWidth = tileMapJSONParser.getTileWidth();
        this.tileHeight = tileMapJSONParser.getTileHeight();

        this.tiles = new Tile[this.mapWidth][this.mapHeight];


        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {

                Tile tile = new Tile(x * tileWidth, y * tileHeight);
                boolean isWall = !tileMapJSONParser.isTraversable(x, y);

                if (isWall) {
                    tile.setWall(true);
                }

                this.tiles[x][y] = tile;
            }
        }
    }


    /**
     * the method draw isn't important, its only purpose is to check if the algorithm works
     **/
    public void draw(FXGraphics2D graphics) {
        if (!DIRLAYER_TOSHOW.equals("")) {
            for (int y = 0; y < this.mapHeight; y++) {
                for (int x = 0; x < this.mapWidth; x++) {
                    int pixelX = x * 16;
                    int pixelY = y * 16;

                    if (this.tiles[x][y].getDirections().get(DIRLAYER_TOSHOW) == Direction.ENDPOINT) {
                        graphics.drawImage(sprites[118], pixelX, pixelY, null);

                    } else if (this.tiles[x][y].getDirections().get(DIRLAYER_TOSHOW) == Direction.RIGHT) {
                        graphics.drawImage(sprites[101], pixelX, pixelY, null);

                    } else if (this.tiles[x][y].getDirections().get(DIRLAYER_TOSHOW) == Direction.LEFT) {
                        graphics.drawImage(sprites[103], pixelX, pixelY, null);

                    } else if (this.tiles[x][y].getDirections().get(DIRLAYER_TOSHOW) == Direction.DOWN) {
                        graphics.drawImage(sprites[102], pixelX, pixelY, null);

                    } else if (this.tiles[x][y].getDirections().get(DIRLAYER_TOSHOW) == Direction.UP) {
                        graphics.drawImage(sprites[117], pixelX, pixelY, null);
                    }

                }
            }
        }
    }


    public void setAllRoutes() {

        ArrayList<Point2D> canteen = new ArrayList<>();
        ArrayList<Point2D> LA301 = new ArrayList<>();
        ArrayList<Point2D> LA302 = new ArrayList<>();
        ArrayList<Point2D> LA303 = new ArrayList<>();
        ArrayList<Point2D> LA304 = new ArrayList<>();
        ArrayList<Point2D> LA305 = new ArrayList<>();
        ArrayList<Point2D> entry = new ArrayList<>();


        for (int i = 0; i < this.objectLayer.size(); i++) {

            JsonObject object = this.objectLayer.getJsonObject(i);

            if (object.getString("name").equals("canteen")) {
                int width = object.getInt("width");

                while (width != 0) {
                    width -= 16;
                    canteen.add(new Point2D.Double(object.getInt("x") + width, object.getInt("y")));
                }

            } else if (object.getString("name").equals("LA301") || object.getString("name").equals("LA302") || object.getString("name").equals("LA303") || object.getString("name").equals("LA304")) {
                int height = object.getInt("height");

                String name = object.getString("name");

                while (height != 0) {
                    height -= 16;

                    switch (name) {
                        case "LA301":
                            LA301.add(new Point2D.Double(object.getInt("x"), object.getInt("y") + height));
                            break;

                        case "LA302":
                            LA302.add(new Point2D.Double(object.getInt("x"), object.getInt("y") + height));
                            break;

                        case "LA303":
                            LA303.add(new Point2D.Double(object.getInt("x"), object.getInt("y") + height));
                            break;

                        case "LA304":
                            LA304.add(new Point2D.Double(object.getInt("x"), object.getInt("y") + height));
                            break;
                    }
                }
            } else if (object.getString("name").equals("LA305")) {
                int width = object.getInt("width");

                while (width != 0) {
                    width -= 16;
                    LA305.add(new Point2D.Double(object.getInt("x") + width, object.getInt("y")));
                }

            } else if(object.getString("name").equals("entry")){
                int width = object.getInt("width");
                while (width != 0){
                    width -= 16;
                    entry.add(new Point2D.Double(object.getInt("x") + width, object.getInt("y")));

                }
            }
        }

        addRoute(canteen, "canteen");
        addRoute(LA301, "LA301");
        addRoute(LA302, "LA302");
        addRoute(LA303, "LA303");
        addRoute(LA304, "LA304");
        addRoute(LA305, "LA305");
        addRoute(entry, "entry");

    }


    private Queue<Tile> nextList;

    public void addRoute(ArrayList<Point2D> destinations, String route) {


        nextList = new LinkedList<Tile>();
        for (Point2D point : destinations) {
            int x = (int) (point.getX() / 16);
            int y = (int) (point.getY() / 16);


            if (isInGrid(x, y)) {

                this.tiles[x][y].setDestination(true);
                this.tiles[x][y].getDirections().put(route, Direction.ENDPOINT);
                this.tiles[x][y].setHasBeenSet(true);
                CheckNonDiagonalNeighbours(x, y, route);
            }
        }

//
//
//        if(isInGrid(x1,y1) && isInGrid(x2,y2)){
//            nextList = new LinkedList<Tile>();
//
//            this.tiles[y1][x1].setDestination(true);
//            this.tiles[y1][x1].getDirections().put(route, Direction.ENDPOINT);
//            this.tiles[y1][x1].setHasBeenSet(true);
//
//            this.tiles[y2][x2].setDestination(true);
//            this.tiles[y2][x2].getDirections().put(route, Direction.ENDPOINT);
//            this.tiles[y2][x2].setHasBeenSet(true);
//
//
//            CheckNonDiagonalNeighbours(x1, y1, route);
//            CheckNonDiagonalNeighbours(x2, y2, route);

        while (!nextList.isEmpty()) {

            Tile checkTile = nextList.poll();
            CheckNonDiagonalNeighbours((int) checkTile.getGridPos().getX() / 16, (int) checkTile.getGridPos().getY() / 16, route);
        }

        for (Point2D point : destinations) {
            int x = (int) (point.getX() / 16);
            int y = (int) (point.getY() / 16);

            this.tiles[x][y].setDestination(false);
        }

//        this.tiles[y1][x1].setDestination(false);
//        this.tiles[y2][x2].setDestination(false);

        for (int yTile = 0; yTile < this.mapHeight; yTile++) {
            for (int xTile = 0; xTile < this.mapWidth; xTile++) {
                this.tiles[xTile][yTile].setHasBeenSet(false);
            }
        }
    }


    private boolean isInGrid(int x, int y) {

        if (x < this.mapWidth && x > -1 && y < this.mapHeight && y > -1) {
            return true;
        } else {
            return false;
        }
    }


    private void CheckNonDiagonalNeighbours(int x, int y, String route) {

        if (isInGrid(x, y + 1)) {

            final Tile checkTile = this.tiles[x][y + 1];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.UP);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x + 1, y)) {

            final Tile checkTile = this.tiles[x + 1][y];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.LEFT);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x, y - 1)) {

            final Tile checkTile = this.tiles[x][y - 1];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.DOWN);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x - 1, y)) {

            final Tile checkTile = this.tiles[x - 1][y];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.RIGHT);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

    }


    public Tile[][] getTiles() {
        return tiles;
    }
}
