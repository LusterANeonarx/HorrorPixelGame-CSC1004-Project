import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
public class Main extends JFrame implements KeyListener,Runnable {
    private Image offScreenImage = null;
    private Player player = new Player();
    private Thread thread = new Thread(this);

    public Main(){
        //set the screen
        this.setSize(1600,1200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.addKeyListener(this);
        this.setTitle("Horror Pixel");

        //init imag
        staticValue.init();

        player = new Player(400,300);


        repaint();
        thread.start();
    }

    //put images on g
    @Override
    public void paint(Graphics g){
        if(offScreenImage == null){
            offScreenImage = createImage(1600,1200);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,1600,1200);
        //background

        graphics.drawImage(player.getShow(), player.getX(), player.getY(), this);
        g.drawImage(offScreenImage,0,0,this);
    }

    //run the frame
    public static void main(String[] args){
        Main myFrame = new Main();
    }
    @Override
    public void keyTyped(KeyEvent e){

    }

    //keyboard listener:
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 39){
            player.Right();
        }
        if(e.getKeyCode() ==37){
            player.Left();
        }
        if(e.getKeyCode() == 38){
            player.Up();
        }
        if(e.getKeyCode() ==40){
            player.Down();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37){
            player.leftStop();
        }
        if(e.getKeyCode()==39){
            player.rightStop();
        }
        if(e.getKeyCode()==38){
            player.upStop();
        }
        if(e.getKeyCode()==40){
            player.downStop();
        }
    }

    //refresh the screen
    @Override
    public void run() {
        while(true){
            repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
















