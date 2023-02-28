import java.awt.image.BufferedImage;

public class Player implements Runnable{
    private int x;
    private int y;
    private String status;
    private BufferedImage show = null;
    private Thread thread = null;
    private int xSpeed;
    private int ySpeed;
    private int index=0;
    public Player(){

    }
    public Player(int x,int y){
        this.x = x;
        this.y = y;
        show = staticValue.standDown;
        this.status = "standdown";
        thread = new Thread(this);
        thread.start();
    }
    public void Left(){
        xSpeed = -15;
        status = "moveleft";
    }
    public void Right(){
        xSpeed = 15;
        status = "moveright";
    }
    public void Up(){
        ySpeed = -15;
        status = "moveup";
    }
    public void Down(){
        ySpeed = 15;
        status = "movedown";
    }

    public void leftStop(){
        xSpeed = 0;
        status = "standleft";
    }
    public void rightStop(){
        xSpeed = 0;
        status = "standright";
    }
    public void upStop(){
        ySpeed = 0;
        status = "standup";
    }
    public void downStop(){
        ySpeed = 0;
        status = "standdown";
    }

    @Override
    public void run() {
        while(true){
            if(xSpeed!=0){
                x+=xSpeed;
            }
            //judge screen
            if(x<0){
                x=0;
            }
            if(x>800){
                x=800;
            }
            if(ySpeed!=0){
                y+=ySpeed;
            }
            //judge screen
            if(y<0){
                y=0;
            }
            if(y>600){
                y=600;
            }
            if(status.contains("move")){
                index ++;
                if(index==6){
                    index=0;
                }
            }
            if("moveleft".equals(status)){
                show = staticValue.walkLeft.get(index/2);
            }
            if("moveright".equals(status)){
                show = staticValue.walkRight.get(index/2);
            }
            if("moveup".equals(status)){
                show = staticValue.walkUp.get(index/2);
            }
            if("movedown".equals(status)){
                show = staticValue.walkDown.get(index/2);
            }
            //move--stand
            if("stopup".equals(status)){
                show = staticValue.standUp;
            }
            if("stopdown".equals(status)){
                show = staticValue.standDown;
            }
            if("stopleft".equals(status)){
                show = staticValue.standLeft;
            }
            if("stopright".equals(status)){
                show = staticValue.standRight;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }
}






































