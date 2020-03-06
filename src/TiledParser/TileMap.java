package TiledParser;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileMap {
    private TileMapJSONParser tileMapJSONParser;
    private BufferedImage sourceImage;
    private BufferedImage[] sprites;

    private int mapWidth, mapHeight;
    private int spriteWidth, spriteHeight;

    public TileMap(String jsonFilename) throws IOException {
        this.tileMapJSONParser = new TileMapJSONParser(new File(jsonFilename));

        this.mapWidth = tileMapJSONParser.getWidth();
        this.mapHeight = tileMapJSONParser.getHeight();
        this.spriteWidth = tileMapJSONParser.getTileWidth();
        this.spriteHeight = tileMapJSONParser.getTileHeight();

        //this.sourceImage = ImageIO.read(getClass().getResource("resources/" + tileMapJSONParser.getTilesetImageSource()));
        this.sourceImage = ImageIO.read(new File("resources/" + tileMapJSONParser.getTilesetImageSource()));
        this.sprites = Spriteloader.getImages(sourceImage, spriteWidth, spriteHeight);

    }

    public void draw(FXGraphics2D graphics, Camera camera) {
        for (int layer = 0; layer < tileMapJSONParser.getLayersAmount() - 1; layer++) {
            for (int yPos = 0; yPos < mapHeight; yPos++) {
                for (int xPos = 0; xPos < mapWidth; xPos++) {
                    int spriteID = tileMapJSONParser.getTileData(layer, xPos, yPos);
                    if (spriteID != -1) {
                        graphics.drawImage(sprites[spriteID], camera.getXOffset() + (xPos * spriteWidth), camera.getYOffset() + (yPos * spriteHeight), null);
                    }
                }
            }
        }
    }
}
