package TiledParser;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TileMap {
    private TileMapJSONParser tileMapJSONParser;
    private BufferedImage sourceImage;

    private int mapWidth;
    private int mapHeight;
    private int spriteWidth;
    private int spriceHeight;

    public TileMap(String filename) throws IOException{
        this.tileMapJSONParser = new TileMapJSONParser(new File(filename));
        this.sourceImage = ImageIO.read(getClass().getResource("/spritesheet.png"));

        this.mapWidth = tileMapJSONParser.getWidth();
        this.mapHeight = tileMapJSONParser.getHeight();
        this.spriteWidth = tileMapJSONParser.getTileWidth();
        this.spriceHeight = tileMapJSONParser.getTileHeight();

        //Cut images
        int ID = 0;
        for (int yPos = 0; yPos < mapWidth; yPos += spriteWidth) {
            for (int xPos = 0; xPos < mapHeight; xPos += spriceHeight) {
                System.out.println("X: " + xPos + " - Y: " + yPos + " - ID: " + ID);

                ID++;
            }

        }
    }

    public void draw(FXGraphics2D graphics) {
        int width = tileMapJSONParser.getWidth();
        int height = tileMapJSONParser.getHeight();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; height++) {

            }

        }
    }
}
