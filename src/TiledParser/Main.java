package TiledParser;

import javax.json.JsonObject;
import java.io.File;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) throws Exception{
        //launch(Simulation.class);
        TileMapJSONParser tileMapJSONParser = new TileMapJSONParser(new File("src/TiledParser/map.json"));
        JsonObject jsonObject = tileMapJSONParser.getJsonObject();
        System.out.print(tileMapJSONParser.getTileData(2, 52, 1));


        //System.out.println(tileMapJSONParser.getWidth());

    }
}
