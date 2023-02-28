import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class staticValue {
    //public static BufferedImage bg1 = null;

    //player walk
    public static List<BufferedImage> walkLeft = new ArrayList<>();
    public static List<BufferedImage> walkRight = new ArrayList<>();
    public static List<BufferedImage> walkUp = new ArrayList<>();
    public static List<BufferedImage> walkDown = new ArrayList<>();
    public static BufferedImage standLeft = null;
    public static BufferedImage standRight = null;
    public static BufferedImage standUp = null;
    public static BufferedImage standDown = null;
    //obstacle
    //public static List<BufferedImage> wall = new ArrayList<>();

    //public static List<BufferedImage> enemy = new ArrayList<>();

    public static String path = System.getProperty("user.dir")+"/src/main/java/images/";

    public static void init(){
        try {
            standLeft = ImageIO.read(new File(path+"$Dr-Frankenstien_05.gif"));
            standRight = ImageIO.read(new File(path+"$Dr-Frankenstien_08.gif"));
            standUp = ImageIO.read(new File(path+"$Dr-Frankenstien_11.gif"));
            standDown = ImageIO.read(new File(path+"$Dr-Frankenstien_02.gif"));

        } catch (IOException e){
            e.printStackTrace();
        }

        for (int i=1;i<=3;i++) {
            try{
                walkDown.add(ImageIO.read(new File(path + "$Dr-Frankenstien_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        for (int i=4;i<=6;i++) {
            try{
                walkLeft.add(ImageIO.read(new File(path + "$Dr-Frankenstien_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        for (int i=7;i<=9;i++) {
            try{
                walkRight.add(ImageIO.read(new File(path + "$Dr-Frankenstien_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        for (int i=10;i<=12;i++) {
            try{
                walkUp.add(ImageIO.read(new File(path + "$Dr-Frankenstien_"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}




















