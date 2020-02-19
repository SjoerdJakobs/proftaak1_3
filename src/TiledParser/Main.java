package TiledParser;

import javax.json.JsonObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) throws Exception {

//        TileMap tileMap = new TileMap("resources/map.json");
//        tileMap.draw(null);

        launch(Simulation.class);
//
//        TileMapJSONParser tileMapJSONParser = new TileMapJSONParser(new File("resources/test.json"));
//        int spriteID = tileMapJSONParser.getTileData(0, 0, 4);
//        BufferedImage bufferedImage = new BufferedImage(1024,1024,BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        //System.out.println(tileMapJSONParser.getWidth());

    }
}