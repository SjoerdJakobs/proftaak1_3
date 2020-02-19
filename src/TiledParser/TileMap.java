package TiledParser;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TileMap {
    private TileMapJSONParser tileMapJSONParser;
    private BufferedImage sourceImage;
    private BufferedImage[] sprites;

    private int mapWidth;
    private int mapHeight;
    private int spriteWidth;
    private int spriceHeight;

    public TileMap(String jsonFilename) throws IOException {
        this.tileMapJSONParser = new TileMapJSONParser(new File(jsonFilename));

        this.mapWidth = tileMapJSONParser.getWidth();
        this.mapHeight = tileMapJSONParser.getHeight();
        this.spriteWidth = tileMapJSONParser.getTileWidth();
        this.spriceHeight = tileMapJSONParser.getTileHeight();

        //this.sourceImage = ImageIO.read(getClass().getResource("resources/" + tileMapJSONParser.getTilesetImageSource()));
        this.sourceImage = ImageIO.read(new File("resources/" + tileMapJSONParser.getTilesetImageSource()));
        this.sprites = Spriteloader.getImages(sourceImage, spriteWidth, spriceHeight);

    }

    public void draw(FXGraphics2D graphics) {
        for (int layer = 0; layer < tileMapJSONParser.getLayersAmount() - 1; layer++) {
            System.out.println("Layer: " + layer);
            for (int yPos = 0; yPos < mapHeight; yPos++) {
                for (int xPos = 0; xPos < mapWidth; xPos++) {
                    int spriteID = tileMapJSONParser.getTileData(layer, xPos, yPos);
                    if (spriteID != -1) {
                        graphics.drawImage(sprites[spriteID], xPos * spriteWidth, yPos * spriceHeight, null);
                    }
                }
            }
        }
    }
}
