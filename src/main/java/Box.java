import javax.swing.*;
import java.awt.image.BufferedImage;

public class Box extends Obstacle{
    private BufferedImage show = null;
    public String info;
    Box(int x, int y, int type, Background bg, String info,Game game) {
        // its parent is obstacle, it is to differenciate box from obstacle because getting close to a box will cause dialogue
        super(x, y, type, bg);
        show = StaticValues.tiles.get(type);
        this.info = info;
    }



}
