package TiledParser;

import javax.json.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TileMapJSONParser {

    private JsonObject jsonObject;

    public TileMapJSONParser(File file) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        JsonReader jsonReader = Json.createReader(inputStream);
        jsonObject = jsonReader.readObject();
    }

    public JsonObject getTileset(int id){
        JsonArray tilesets = jsonObject.getJsonArray("tilesets");
        return tilesets.getJsonObject(id);
    }

    public String getTilesetImageSource(){
        JsonObject tileset = getTileset(0);
        return tileset.getString("image");
    }

    public int getWidth() {
        return jsonObject.getInt("width");
    }

    public int getHeight() {
        return jsonObject.getInt("height");
    }

    public int getTileWidth() {
        return jsonObject.getInt("tilewidth");
    }

    public int getTileHeight() {
        return jsonObject.getInt("tileheight");
    }

    public JsonArray getLayers() {
        return jsonObject.getJsonArray("layers");
    }

    public JsonObject getLayer(int layer) {
        JsonArray layersArray = getLayers();
        return layersArray.getJsonObject(layer);
    }

    public JsonObject getLayer(String layer) {
        JsonArray layersArray = getLayers();
        for (int i = 0; i < layersArray.size(); i++) {
            String layerName = layersArray.getJsonObject(i).getString("name");
            if (layerName.equals(layer)) {
                return layersArray.getJsonObject(i);
            }
        }
        return null;
    }

    public int getTileData(int layer, int xPos, int yPos) {
        JsonObject layerObject = getLayer(layer);
        int tileID = getTileID(xPos, yPos);
        return layerObject.getJsonArray("data").getInt(tileID) - 1;
    }

    public int getTileID(int xPos, int yPos) {
        return (yPos * 100) + xPos;
    }


    public JsonObject getJsonObject() {
        return this.jsonObject;
    }
}
