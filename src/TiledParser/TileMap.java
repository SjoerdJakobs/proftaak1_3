package TiledParser;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileMap {
    private TileMapJSONParser tileMapJSONParser;
    private BufferedImage sourceImage;
    private BufferedImage[] sprites;
    private BufferedImage mapImage;

    private int mapWidth, mapHeight;
    private int spriteWidth, spriteHeight;

    public TileMap(String jsonFilename) throws IOException {
        this.tileMapJSONParser = new TileMapJSONParser(new File(jsonFilename));

        //Save a bunch of constants privately for quick/easy access.
        this.mapWidth = tileMapJSONParser.getWidth();
        this.mapHeight = tileMapJSONParser.getHeight();
        this.spriteWidth = tileMapJSONParser.getTileWidth();
        this.spriteHeight = tileMapJSONParser.getTileHeight();

        //Load the spritesheet from disk.
        this.sourceImage = ImageIO.read(new File("resources/" + tileMapJSONParser.getTilesetImageSource()));

        //Load the sprites into an array where their index equals their ID
        this.sprites = Spriteloader.getImages(sourceImage, spriteWidth, spriteHeight);

        //Calculate the amount of pixels we need to draw the map onto an image
        int mapImageWidth = mapWidth * spriteWidth;
        int mapImageHeight = mapHeight * spriteHeight;

        //Create an image to draw the map on and get the drawable graphics
        mapImage = new BufferedImage(mapImageWidth, mapImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics imageGraphics = mapImage.getGraphics();

        //Draw every layer (bottom up)
        for (int layer = 0; layer < tileMapJSONParser.getLayersAmount() - 1; layer++) {
            for (int yPos = 0; yPos < mapHeight; yPos++) { //Draw every row
                for (int xPos = 0; xPos < mapWidth; xPos++) { //Draw every column
                    //Get the ID of the sprite that needs to be drawn at this position
                    int spriteID = tileMapJSONParser.getTileData(layer, xPos, yPos);
                    if (spriteID != -1) { //Make sure we don't draw anything when we need to draw an empty/transparent space
                        imageGraphics.drawImage(sprites[spriteID], (xPos * spriteWidth), (yPos * spriteHeight), null);
                    }
                }
            }
        }
    }

    public void draw(FXGraphics2D graphics2D, Camera camera) {
        //Draw the image on the main canvas
        graphics2D.drawImage(mapImage, 0, 0, null);
    }
}
