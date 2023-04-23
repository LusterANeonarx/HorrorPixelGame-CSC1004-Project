import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements Runnable{
    private JFrame f = new JFrame();
    public Menu menu;
    public Dialogue dialogue;
    private List<Background> allBg = new ArrayList<>();
    private Background nowBg = new Background();

    //for double images
    private Image offScreenImage = null;

    private Hero hero = new Hero();
    public Enemy enemy = new Enemy();
    private Thread thread = new Thread(this);
    Boolean enemyflag = false;
    public boolean truth = false;


    public boolean pause = false;// if x is pressed   if z pressed use message from hero

    //the number of the background
    private int scene = 0;

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }



    //the flag of previous background
    public int flag = 0;

   Game(){

   }
   Game(JFrame f,Menu menu,Dialogue dialogue) {
       this.f = f;
       this.menu = menu;
       this.dialogue = dialogue;
       setBounds(0, 0, 768, 576);
       StaticValues.init();



       addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {

           }

           @Override
           public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == 39) {
                   hero.Right();
               }
               if (e.getKeyCode() == 37) {
                   hero.Left();
               }
               if (e.getKeyCode() == 38) {
                   hero.Up();
               }
               if (e.getKeyCode() == 40) {
                   hero.Down();
               }

               if(e.getKeyCode() == 88){
                   if(pause){//when x pressed everything went pause
                       pause=false;
                   }
                   else{
                       pause = true;
                   }
               }

               if(e.getKeyCode() == 90){
                   if(!pause) {
                       if (!hero.getMessage()) {
                           hero.setMessage(true);//the message of z pressed is into hero
                       } else {
                           hero.setMessage(false);
                       }
                   }
               }
           }

           @Override
           public void keyReleased(KeyEvent e) {
               if (e.getKeyCode() == 37) {
                   hero.leftStop();
               }
               if (e.getKeyCode() == 39) {
                   hero.rightStop();
               }
               if (e.getKeyCode() == 38) {
                   hero.upStop();
               }
               if (e.getKeyCode() == 40) {
                   hero.downStop();
               }
           }
       });

       for (int i = 0; i <= 6; i++) {
           allBg.add(new Background(i,this,hero));
       }
       repaint();
       thread.start();
   }

    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(768, 576);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 768, 576);

        graphics.drawImage(nowBg.getBgImage(), 0, 0, 768, 576, this);
        for(Obstacle ob : nowBg.getObstacleList()){
            graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
        }
        for(Box box : nowBg.getBoxList()){
            if(box.info.equals("key1")&&hero.key1){//hero get key1 must disappear
                continue;
            }
            graphics.drawImage(box.getShow(),box.getX(),box.getY(),this);
        }
        graphics.drawImage(enemy.getShow(), enemy.getX(), enemy.getY(), this);
        graphics.drawImage(hero.getShow(), hero.getX(), hero.getY(), this);


        g.drawImage(offScreenImage, 0, 0, this);


    }

    @Override
    public void run() {
       int i = 0;
        while (true) {
            System.out.println(scene);
            repaint();
            if(pause){
                hero.setEmpty(true);// make hero unable to move
                enemy.setEmpty(true);//make enemy unable to move
                menu.setVisible(true);//menu
                menu.setFocusable(true);
            }
            else{
                menu.setVisible(false);//no menu
                this.setFocusable(true);
            }


            if(hero.getMessage()&&hero.returnMessage){//hero on box and pressed z
                hero.setEmpty(true);// make hero unable to move
                enemy.setEmpty(true);//make enemy unable to move
                dialogue.setVisible(true);
            }
            if(!hero.getMessage()&&hero.returnMessage){//hero on box and pressed z again
                hero.returnMessage = false;
                dialogue.setVisible(false);
                hero.setEmpty(false);
                enemy.setEmpty(false);
                hero.returnMessage = false;
            }

            if(!pause&&!hero.returnMessage){//if neither x nor z pressed
                hero.setEmpty(false);// make hero able to move
                enemy.setEmpty(false);//make enemy able to move
            }


            if(scene==0){
                nowBg = allBg.get(scene);//you are a private detective...
                if(i<=100){
                    i++;
                }
                else{
                    scene = 1;
                    i=0;
                }
            }

            if(scene==1&&flag!=1){//front door
                flag =1;
                nowBg = allBg.get(scene);
                hero = new Hero(768/2, 270, "standup",this,1);
                hero.setBg(nowBg);
            }



            if(scene==2&&flag!=2){//inside room
                flag=2;//flag is to see whether scene is really in 2, if no, switch to 2. if yes, stay the same
                nowBg = allBg.get(scene);
                hero = new Hero(560, 350, "standleft",this,2);
                hero.setBg(nowBg);
            }

            if(scene==3&&flag!=3){//chasing scene
                flag=3;//flag is to see whether scene is really in 3, if no, switch to 3. if yes, stay the same
                nowBg = allBg.get(scene);
                hero = new Hero(60,10,"standright",this,3);
                enemy = new Enemy(-40,10,hero,this,4);
                hero.setBg(nowBg);
            }
            if(scene==4&&flag!=4){//ending 1: found the death reason
                flag=4;//flag is to see whether scene is really in 4, if no, switch to 4. if yes, stay the same
                nowBg = allBg.get(scene);
                hero = new Hero();
                enemy = new Enemy();
                hero.setEmpty(true);
                enemy.setEmpty(true);
            }
            if(scene==5&&flag!=5){//ending 2: only escaped
                flag=5;//flag is to see whether scene is really in 5, if no, switch to 5. if yes, stay the same
                nowBg = allBg.get(scene);
                hero = new Hero();
                enemy = new Enemy();
                hero.setEmpty(true);
                enemy.setEmpty(true);
            }
            if(scene==6&&flag!=6){//ending 3: get caught by enemy
                flag=6;//flag is to see whether scene is really in 6, if no, switch to 6. if yes, stay the same
                nowBg = allBg.get(scene);
                hero = new Hero();
                enemy = new Enemy();
                hero.setEmpty(true);
                enemy.setEmpty(true);
            }

            if (hero.behindyouflag&&!enemyflag){//let enemy appear
                enemy = new Enemy(560,350,hero,this,3);
                enemyflag = true;
            }
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
