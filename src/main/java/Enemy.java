import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class Enemy implements Runnable{
    //boolean on thread
    private boolean empty = false;
    public boolean lock = true;
    //enemy position
    private int x;
    private int y;
    private Hero hero;
    private int xSpeed;
    private int ySpeed;
    int speed;
    private BufferedImage show = null;
    private Background bg = new Background();
    private Game game = null;

    private String status;

    //hero thread
    public Thread thread = null;
    private int index=0;
    Enemy(){

    }
    Enemy(int x, int y,Hero hero,Game game,int speed){
        this.x = x;
        this.y = y;
        this.hero = hero;
        this.speed = speed;
        this.game = game;
        show = StaticValues.estandLeft;
        thread = new Thread(this);
        thread.start();
    }
    //walking functions
    public void Left(){
        xSpeed = -speed;
        status = "moveleft";
    }
    public void Right(){
        xSpeed = speed;
        status = "moveright";
    }
    public void Up(){
        ySpeed = -speed;
        status = "moveup";
    }
    public void Down(){
        ySpeed = speed;
        status = "movedown";
    }
    @Override
    public void run() {//thread
        while(true){
            if(!empty&&!lock) {//if it can move
                int hx = hero.getX() + 36;
                int hy = hero.getY() + hero.getShow().getHeight();

                int ex = getX()+ 36;
                int ey = getY() + getShow().getHeight();


                //chase hero
                if(hx>ex+speed){
                    Right();
                }
                if(hx<ex-speed){
                    Left();
                }
                if(hy>ey+speed){
                    Down();
                }
                if(hy<ey-speed){
                    Up();
                }


                //prevent enemy shaking
                if(abs(hx-ex)<=speed){
                    xSpeed = 0;
                }
                if(abs(hy-ey)<=speed){
                    ySpeed = 0;
                }

                //enemy moving
                x += xSpeed;
                y += ySpeed;

                //enemy graph show
                if (status.contains("move")) {
                    index++;
                    if (index == 6) {
                        index = 0;
                    }
                }
                if ("moveleft".equals(status)) {
                    show = StaticValues.ewalkLeft.get(index / 2);
                }
                if ("moveright".equals(status)) {
                    show = StaticValues.ewalkRight.get(index / 2);
                }
                if ("moveup".equals(status)) {
                    show = StaticValues.ewalkUp.get(index / 2);
                }
                if ("movedown".equals(status)) {
                    show = StaticValues.ewalkDown.get(index / 2);
                }



                //if enemy catch hero
                if(getBounds().intersects(hero.getBounds())&&(game.getScene()==2||game.getScene()==3)){
                    game.setScene(6);
                }

            }
            try {
                Thread.sleep(50);
                //condition
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getX() {
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    public Rectangle getBounds(){
        return new Rectangle(x+12,y+show.getHeight()-20,48,20);
    }
}
