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
        this.sourceImage = ImageIO.read(new File("resources/tileset_16x16_interior.png"));
        this.sprites = Spriteloader.getImages(sourceImage, spriteWidth, spriceHeight);

    }

    public void draw(FXGraphics2D graphics) {
        int layer = 0;
        for (int yPos = 0; yPos < mapHeight; yPos++) {
            for (int xPos = 0; xPos < mapWidth; xPos++) {
                int spriteID = tileMapJSONParser.getTileData(layer, xPos, yPos);
                graphics.drawImage(sprites[spriteID], xPos, yPos, null);
            }
        }
    }
}
