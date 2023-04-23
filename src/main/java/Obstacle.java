
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {
    private int x;
    private int y;
    private int type;
    private Background bg;
    private BufferedImage show = null;
    Obstacle(int x, int y, int type,Background bg){
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValues.tiles.get(type);
    }

    //find if it intersects with hero
    public Rectangle getBounds(){
        return new Rectangle(x,y,show.getWidth(),show.getHeight());
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }
}
