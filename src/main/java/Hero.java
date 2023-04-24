import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero implements Runnable{
    //boolean on thread
    private boolean empty=false;
    private boolean message = false;
    public boolean returnMessage = false;//when has message and approach the box
    //hero position
    private int x=-100;
    private int y=-100;
    //hero speed
    private int xSpeed;
    private int ySpeed;
    int speed = 10;
    //hero images
    private BufferedImage show = null;
    private Background bg = new Background();
    private Game game = null;

    //hero thread
    public Thread thread = null;
    //hero status
    private String status;
    //hero movement index
    private int index=0;

    //hero's scene
    public int scene;



    boolean key1 = false;
    boolean behindyouflag = false;

    jdbc connector = new jdbc();
    public Hero(){

    }
    public Hero(int x, int y,String status,Game game,int scene){
        this.x = x;
        this.y = y;
        this.scene = scene;
        this.game = game;
        this.status = status;
        //set the position of hero
        if(status.equals("standup")){
            show = StaticValues.standUp;
        }
        if(status.equals("standdown")){
            show = StaticValues.standDown;
        }
        if(status.equals("standleft")){
            show = StaticValues.standLeft;
        }
        if(status.equals("standright")){
            show = StaticValues.standRight;
        }

        thread = new Thread(this);
        thread.start();
    }

    //set the movement of hero
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


    //when hero stop, its status
    public void leftStop(){
        xSpeed = 0;
        if(!empty) {//hero can turn only when he can move
            status = "standleft";
            show = StaticValues.standLeft;
        }
    }
    public void rightStop(){
        xSpeed = 0;
        if(!empty) {
            status = "standright";
            show = StaticValues.standRight;
        }
    }
    public void upStop(){
        ySpeed = 0;
        if(!empty) {
            status = "standup";
            show = StaticValues.standUp;
        }
    }
    public void downStop(){
        ySpeed = 0;
        if(!empty) {
            status = "standdown";
            show = StaticValues.standDown;
        }
    }
    @Override
    public void run() {
        while(true){
            if(!empty) {
                //hero movement
                x += xSpeed;
                y += ySpeed;




                //if hit obstacle
                for (int i = 0; i < bg.getObstacleList().size(); i++) {

                    Obstacle ob = bg.getObstacleList().get(i);
                    if (ob.getBounds().intersects(getBounds())) {
                        x -= xSpeed;
                        y -= ySpeed;
                    }
                }

                //if hit box
                int messageflag = 0;

                JLabel sentence = new JLabel();
                sentence.setBounds(0,-50,758,200);
                JLabel sentence2 = new JLabel();
                sentence2.setBounds(0,-20,758,200);

                for (int i = 0; i < bg.getBoxList().size(); i++) {
                    Box box = bg.getBoxList().get(i);
                    if (box.getBounds().intersects(getBounds1())) {//approach the box
                        if (message) {
                            messageflag = 1;
                            sentence2.setText("");
                            if(box.info.equals("key1")&&!key1){
                                returnMessage = true;//show dialogue
                                game.dialogue.removeAll();
                                sentence.setText("You have found the front door key.");
                                key1 = true;
                            }
                            if(box.info.equals("front_door")&&game.getScene()==1){
                                if(key1){
                                    returnMessage = false;// not show dialogue
                                    game.setScene(2); // came to scene2

                                }
                                else {
                                    returnMessage = true;
                                    game.dialogue.removeAll();
                                    sentence.setText("The door is locked, no one answers the door.");
                                }
                            }
                            if (box.info.equals("door2")&&game.getScene()==2){
                                if(behindyouflag){
                                    returnMessage = false;
                                    game.setScene(3);
                                }
                                else{
                                    returnMessage = true;
                                    game.dialogue.removeAll();
                                    sentence.setText("You cannot exit because the You have to do your detective stuffs.");
                                }
                            }
                            if(box.info.equals("bed")){
                                returnMessage = true;//show dialogue
                                game.dialogue.removeAll();
                                sentence.setText("A bed with blood on it. Stinky and disgusting.");
                            }
                            if(box.info.equals("letter")){
                                returnMessage = true;//show dialogue
                                game.dialogue.removeAll();
                                sentence.setText("A piece of paper which writes: Someone is BEHIND you! R U N!!");
                                behindyouflag = true;
                            }
                            if(box.info.equals("diary")){
                                if(behindyouflag){
                                    returnMessage = true;
                                    game.dialogue.removeAll();
                                    game.truth = true;
                                    sentence.setText("The diary is now open: " +
                                            "I am a CSC1004 student who cannot bear the homework anymore. I decide to kill myself today."
                                            );
                                    sentence2.setText("Oh, no! I won't die now......I will become a ghost, and kill all the people alive!!!");
                                }
                                else{
                                    returnMessage = true;
                                    game.dialogue.removeAll();
                                    sentence.setText("The diary is stick with blood, you cannot open it for now.");
                                }
                            }
                            if(box.info.equals("door3")&&game.getScene()==3){
                                if(game.truth){
                                    returnMessage = false;
                                    game.setScene(4);
                                }
                                else{
                                    returnMessage = false;
                                    game.setScene(5);
                                }
                            }

                            game.dialogue.add(sentence);
                            game.dialogue.add(sentence2);





                        }
                    }
                    if (box.getBounds().intersects(getBounds())) {//hit the box
                        x -= xSpeed;
                        y -= ySpeed;
                    }
                }


                if(messageflag==0){
                    message = false;//not approach any boxes but pressed z
                }





                // when moving, show the hero movement
                if (status.contains("move")) {
                    index++;
                    if (index == 6) {
                        index = 0;
                    }
                }
                if ("moveleft".equals(status)) {
                    show = StaticValues.walkLeft.get(index / 2);
                }
                if ("moveright".equals(status)) {
                    show = StaticValues.walkRight.get(index / 2);
                }
                if ("moveup".equals(status)) {
                    show = StaticValues.walkUp.get(index / 2);
                }
                if ("movedown".equals(status)) {
                    show = StaticValues.walkDown.get(index / 2);
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

    public Rectangle getBounds(){
        return new Rectangle(x+12,y+show.getHeight()-20,48,20);
    }
    public Rectangle getBounds1(){return new Rectangle(x+2,y+show.getHeight()-30,68,40);}
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

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }
    public void setEmpty(boolean empty){
        this.empty = empty;
    }


    public void setMessage(boolean message) {
        this.message = message;
    }
    public boolean getMessage(){
        return message;
    }
    public void setStatus(String status){
        this.status = status;
    }

}
