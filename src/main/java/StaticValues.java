import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValues {//this is for pictures import
    public  static BufferedImage bg0 = null;
    public  static BufferedImage bg1 = null;
    public  static BufferedImage bg2 = null;
    public  static BufferedImage bg3 = null;
    public  static BufferedImage bg4 = null;
    public  static BufferedImage bg5 = null;
    public  static BufferedImage bg6 = null;
    public static List<BufferedImage> walkLeft = new ArrayList<>();
    public static List<BufferedImage> walkRight = new ArrayList<>();
    public static List<BufferedImage> walkUp = new ArrayList<>();
    public static List<BufferedImage> walkDown = new ArrayList<>();
    public static BufferedImage standLeft = null;
    public static BufferedImage standRight = null;
    public static BufferedImage standUp = null;
    public static BufferedImage standDown = null;


    public static List<BufferedImage> ewalkLeft = new ArrayList<>();
    public static List<BufferedImage> ewalkRight = new ArrayList<>();
    public static List<BufferedImage> ewalkUp = new ArrayList<>();
    public static List<BufferedImage> ewalkDown = new ArrayList<>();
    public static BufferedImage estandLeft = null;
    public static BufferedImage estandRight = null;
    public static BufferedImage estandUp = null;
    public static BufferedImage estandDown = null;


    public static BufferedImage portal = null;


    public static List<BufferedImage> tiles = new ArrayList<>();
    public static String path = System.getProperty("user.dir")+"/src/main/java/images/";

    public static void init(){
        try {
            bg0 = ImageIO.read(new File(path+"bg0.png"));
            bg1 = ImageIO.read(new File(path+"bg1.png"));
            bg2 = ImageIO.read(new File(path+"bg2.png"));
            bg3 = ImageIO.read(new File(path+"bg3.png"));
            bg4 = ImageIO.read(new File(path+"bg4.png"));
            bg5 = ImageIO.read(new File(path+"bg5.png"));
            bg6 = ImageIO.read(new File(path+"bg6.png"));
            standLeft = ImageIO.read(new File(path+"$Dr-Frankenstien_05.gif"));
            standRight = ImageIO.read(new File(path+"$Dr-Frankenstien_08.gif"));
            standUp = ImageIO.read(new File(path+"$Dr-Frankenstien_11.gif"));
            standDown = ImageIO.read(new File(path+"$Dr-Frankenstien_02.gif"));

            estandLeft = ImageIO.read(new File(path+"$MonsterB_05.gif"));
            estandRight = ImageIO.read(new File(path+"$MonsterB_08.gif"));
            estandUp = ImageIO.read(new File(path+"$MonsterB_11.gif"));
            estandDown = ImageIO.read(new File(path+"$MonsterB_02.gif"));
        } catch (IOException e){
            e.printStackTrace();
        }

        for (int i=1;i<=3;i++){
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



        for (int i=1;i<=3;i++) {
            try{
                ewalkDown.add(ImageIO.read(new File(path + "$MonsterB_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        for (int i=4;i<=6;i++) {
            try{
                ewalkLeft.add(ImageIO.read(new File(path + "$MonsterB_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        for (int i=7;i<=9;i++) {
            try{
                ewalkRight.add(ImageIO.read(new File(path + "$MonsterB_0"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        for (int i=10;i<=12;i++) {
            try{
                ewalkUp.add(ImageIO.read(new File(path + "$MonsterB_"+i+".gif")));
            } catch (IOException e){
                e.printStackTrace();
            }
        }






        try{
            tiles.add(ImageIO.read(new File(path+"Sparkle_03.gif")));//0
            tiles.add(ImageIO.read(new File(path+"airwall-vertical.png")));//1
            tiles.add(ImageIO.read(new File(path+"airwall-horizontal.png")));//2
            tiles.add(ImageIO.read(new File(path+"bed_03.gif")));//3
            tiles.add(ImageIO.read(new File(path+"desks_03.gif")));//4
            tiles.add(ImageIO.read(new File(path+"letter_03.gif")));//5
            tiles.add(ImageIO.read(new File(path+"diary_03.gif")));//6
            tiles.add(ImageIO.read(new File(path+"walls_02.gif")));//7
            tiles.add(ImageIO.read(new File(path+"shell_02.gif")));//8
            tiles.add(ImageIO.read(new File(path+"desks2_03.gif")));//9
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
