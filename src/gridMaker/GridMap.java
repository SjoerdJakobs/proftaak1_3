package gridMaker;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GridMap {

    private JsonObject object;
    private JsonArray collisonData;
    private int[][] map;
    private Tile[][] tiles;

    private int mapWidth;
    private int mapHeight;

    private JsonArray objectLayer;


    public GridMap(JsonArray objectLayer, JsonObject theJsonFile) throws FileNotFoundException {

//        InputStream inputStream = new FileInputStream(new File("resources/mapTest.json"));
//        JsonReader jsonReader = Json.createReader(inputStream);
//        object = jsonReader.readObject();

        this.object = theJsonFile;
        this.objectLayer = objectLayer;

        this.collisonData = object.getJsonArray("layers").getJsonObject(object.getJsonArray("layers").size() - 2).getJsonArray("data");

        this.mapWidth = object.getInt("width");
        this.mapHeight = object.getInt("height");


        // this.map = new int[this.mapHeight][this.mapWidth];
        this.tiles = new Tile[100][100];


        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {

                Tile tile = new Tile(x * 16, y * 16);
                int index = this.collisonData.getInt((y * this.mapWidth) + x);

                if (index == 256) {
                    tile.setWall(true);
                }

                this.tiles[y][x] = tile;
            }
        }
    }


    /**
     * the method draw isn't important, its only purpose is to check if the algorithm works
     **/
    public void draw(FXGraphics2D graphics) {

        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {

                if (this.tiles[y][x].isWall()) {
                    graphics.setColor(Color.red);

                } else if (this.tiles[y][x].getDirections().get("canteen") == Direction.ENDPOINT) {
                    graphics.setColor(Color.white);

                } else if (this.tiles[y][x].getDirections().get("canteen") == Direction.RIGHT) {
                    graphics.setColor(Color.yellow);

                } else if (this.tiles[y][x].getDirections().get("canteen") == Direction.LEFT) {
                    graphics.setColor(Color.blue);

                } else if (this.tiles[y][x].getDirections().get("canteen") == Direction.DOWN) {
                    graphics.setColor(Color.pink);

                } else if (this.tiles[y][x].getDirections().get("canteen") == Direction.UP) {
                    graphics.setColor(Color.green);

                } else {
                    graphics.setColor(Color.black);
                }


                graphics.draw(this.tiles[y][x].getGridRect());
                graphics.setColor(Color.blue);

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
            }
        }

        addRoute(canteen, "canteen");
        addRoute(LA301, "LA301");
        addRoute(LA302, "LA302");
        addRoute(LA303, "LA303");
        addRoute(LA304, "LA304");
        addRoute(LA305, "LA305");

    }


    private Queue<Tile> nextList;

    public void addRoute(ArrayList<Point2D> destinations, String route) {


        nextList = new LinkedList<Tile>();
        for (Point2D point : destinations) {
            int x = (int) (point.getX() / 16);
            int y = (int) (point.getY() / 16);


            if (isInGrid(x, y)) {

                this.tiles[y][x].setDestination(true);
                this.tiles[y][x].getDirections().put(route, Direction.ENDPOINT);
                this.tiles[y][x].setHasBeenSet(true);
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

            this.tiles[y][x].setDestination(false);
        }

//        this.tiles[y1][x1].setDestination(false);
//        this.tiles[y2][x2].setDestination(false);

        for (int yTile = 0; yTile < this.mapHeight; yTile++) {
            for (int xTile = 0; xTile < this.mapWidth; xTile++) {
                this.tiles[yTile][xTile].setHasBeenSet(false);
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

            final Tile checkTile = this.tiles[y + 1][x];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.UP);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x + 1, y)) {

            final Tile checkTile = this.tiles[y][x + 1];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.LEFT);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x, y - 1)) {

            final Tile checkTile = this.tiles[y - 1][x];
            if (!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()) {

                checkTile.getDirections().put(route, Direction.DOWN);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

        if (isInGrid(x - 1, y)) {

            final Tile checkTile = this.tiles[y][x - 1];
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
