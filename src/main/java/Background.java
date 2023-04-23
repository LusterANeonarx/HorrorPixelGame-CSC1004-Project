import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Background {
    private BufferedImage bgImage = null;
    private int scene;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private List<Box> boxList = new ArrayList<>();




    public Background(){

    }
    public Background(int scene,Game game,Hero hero){
        this.scene = scene;
        if(scene == 0){//when in scene 0, just show a background
            bgImage = StaticValues.bg0;
        }
        else if(scene==1){
            bgImage = StaticValues.bg1;
            for(int i = 0;i<16;i++) {// when in scene 1, show background, object and box.
                obstacleList.add(new Obstacle(i*48, 278, 2, this));
            }
            for(int i = 0;i<16;i++) {
                obstacleList.add(new Obstacle(i*48, 383, 2, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(0, 278+i*48, 1, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(768, 278+i*48, 1, this));
            }
            boxList.add(new Box(620, 278, 0, this, "key1", game));//key1 position
            boxList.add(new Box(400, 278, 2, this, "front_door", game));// front door is not open

        }
        else if(scene==2){
            bgImage = StaticValues.bg2;
            for(int i=0;i<6;i++){
                obstacleList.add(new Obstacle(100, 180+i*48, 1, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(630, 260+i*48, 1, this));
            }
            for(int i = 0;i<10;i++) {
                obstacleList.add(new Obstacle(i*48+120, 458, 2, this));
            }
            for(int i = 0;i<5;i++) {
                obstacleList.add(new Obstacle(i*48+120, 160, 2, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(360, 232-i*48, 1, this));
            }
            for(int i = 0;i<6;i++) {
                obstacleList.add(new Obstacle(i*48+360, 290, 2, this));
            }
            obstacleList.add(new Obstacle(205, 260, 4, this));
            boxList.add(new Box(630,420,1,this,"door2",game));
            boxList.add(new Box(267,150,3,this,"bed",game));
            boxList.add(new Box(113,232,5,this,"letter",game));
            boxList.add(new Box(299,260,6,this,"diary",game));



        }
        else if(scene==3){
            bgImage = StaticValues.bg3;
            for (int i = 0; i < 12; i++) {
                obstacleList.add(new Obstacle(22,i*48,1,this));
            }
            for (int i = 0; i < 12; i++) {
                obstacleList.add(new Obstacle(735,i*48,1,this));
            }
            for (int i = 0; i < 16; i++) {
                obstacleList.add(new Obstacle(i*48,40,2,this));
            }
            for (int i = 0; i < 16; i++) {
                obstacleList.add(new Obstacle(i*48,520,2,this));
            }
            boxList.add(new Box(700,520,2,this,"door3",game));
            obstacleList.add(new Obstacle(30,120,7,this));
            obstacleList.add(new Obstacle(430,130,4,this));
            obstacleList.add(new Obstacle(530,270,8,this));
            obstacleList.add(new Obstacle(230,290,9,this));
        }
        else if(scene==4){
            bgImage = StaticValues.bg4;
        }
        else if(scene==5){
            bgImage = StaticValues.bg5;
        }
        else if(scene==6){
            bgImage = StaticValues.bg6;
        }

    }






    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public List<Box> getBoxList() {
        return boxList;
    }
}
