package TiledParser;

import java.awt.image.BufferedImage;

public class Tile {
    private boolean walkable;
    private BufferedImage sprite;

    public Tile(boolean walkable, BufferedImage sprite){
        this.walkable = walkable;
        this.sprite = sprite;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
