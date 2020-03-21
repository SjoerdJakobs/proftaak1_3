package gridMaker;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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


        for(int y = 0; y < this.mapHeight; y++){
            for(int x = 0; x < this.mapWidth; x++){

                Tile tile = new Tile(x * 16, y * 16);
                int index = this.collisonData.getInt((y * this.mapWidth) + x);

                if(index == 256){
                    tile.setWall(true);
                }

                this.tiles[y][x] = tile;
            }
        }

       // BFS();
    }


    /**
     the method draw isn't important, its only purpose is to check if the algorithm works
     **/
    public void draw(FXGraphics2D graphics){

        for(int y = 0; y < this.mapHeight; y++){
            for(int x = 0; x < this.mapWidth; x++){

                if(this.tiles[y][x].isWall()){
                    graphics.setColor(Color.red);

                } else if(this.tiles[y][x].getDirections().get("route0") == Direction.ENDPOINT){
                    graphics.setColor(Color.white);

                } else if(this.tiles[y][x].getDirections().get("route0") == Direction.RIGHT){
                    graphics.setColor(Color.yellow);

                } else if(this.tiles[y][x].getDirections().get("route0") == Direction.LEFT){
                    graphics.setColor(Color.blue);

                } else if(this.tiles[y][x].getDirections().get("route0") == Direction.DOWN){
                    graphics.setColor(Color.pink);

                } else if(this.tiles[y][x].getDirections().get("route0") == Direction.UP){
                    graphics.setColor(Color.green);

                } else{
                    graphics.setColor(Color.black);
                }


                graphics.draw(this.tiles[y][x].getGridRect());
                graphics.setColor(Color.blue);

            }
        }

    }


//    public void setAllRoutes(){
//
//
//
//
//        addRoute(80, 59, "route0");
//    }



    private Queue<Tile> nextList;
    public void addRoute(int x1, int y1, int x2, int y2, String route){

        if(isInGrid(x1,y1) && isInGrid(x2,y2)){
            nextList = new LinkedList<Tile>();

            this.tiles[y1][x1].setDestination(true);
            this.tiles[y1][x1].getDirections().put(route, Direction.ENDPOINT);
            this.tiles[y1][x1].setHasBeenSet(true);

            this.tiles[y2][x2].setDestination(true);
            this.tiles[y2][x2].getDirections().put(route, Direction.ENDPOINT);
            this.tiles[y2][x2].setHasBeenSet(true);


            CheckNonDiagonalNeighbours(x1, y1, route);
            CheckNonDiagonalNeighbours(x2, y2, route);

            while(!nextList.isEmpty()){

                Tile checkTile = nextList.poll();
                CheckNonDiagonalNeighbours((int)checkTile.getGridPos().getX() / 16, (int)checkTile.getGridPos().getY() / 16, route);
            }

            this.tiles[y1][x1].setDestination(false);
            this.tiles[y2][x2].setDestination(false);

            for(int yTile = 0; yTile < this.mapHeight; yTile++){
                for(int xTile = 0; xTile < this.mapWidth; xTile++){
                    this.tiles[yTile][xTile].setHasBeenSet(false);
                }
            }
        }

    }

    private boolean isInGrid(int x, int y){

        if(x < this.mapWidth && x > -1 && y < this.mapHeight && y > -1){
            return true;
        }
        else {
            return false;
        }
    }


    private void CheckNonDiagonalNeighbours(int x, int y, String route){

        if(isInGrid(x, y+1)){

            final Tile checkTile = this.tiles[y+1][x];
            if(!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()){

                checkTile.getDirections().put(route, Direction.UP);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

         if(isInGrid(x+1, y)){

            final Tile checkTile = this.tiles[y][x+1];
            if(!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()){

                checkTile.getDirections().put(route, Direction.LEFT);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

         if(isInGrid(x, y-1)){

            final Tile checkTile = this.tiles[y-1][x];
            if(!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()){

                checkTile.getDirections().put(route, Direction.DOWN);
                checkTile.setHasBeenSet(true);
                nextList.add(checkTile);
            }
        }

         if(isInGrid(x-1, y)){

            final Tile checkTile = this.tiles[y][x-1];
            if(!checkTile.isWall() && !checkTile.isHasBeenSet() && !checkTile.isDestination()){

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
