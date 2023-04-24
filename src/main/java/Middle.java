import javax.swing.*;
import java.awt.*;

public class Middle extends JPanel {
    boolean menuclick = false;
    private JFrame f = new JFrame();
    private JLayeredPane jLayeredPane = new JLayeredPane();
    private Game game = new Game();
    private Menu menu = new Menu(f,this);
    private Dialogue dialogue = new Dialogue();



    Middle(JFrame f){
        this.f = f;
        setLayout(null);
        setBounds(0,0,768,576);

    }

    public void startPlay(){
        //initialize game
        game = new Game(f,menu,dialogue,this);

        //menu, game and dialogue are on the same layered pane
        jLayeredPane.setBounds(0,0,768,576);
        jLayeredPane.add(menu,JLayeredPane.POPUP_LAYER);
        jLayeredPane.add(dialogue,JLayeredPane.PALETTE_LAYER);
        menu.setVisible(false);
        dialogue.setVisible(false);
        jLayeredPane.add(game,JLayeredPane.DEFAULT_LAYER);
        f.add(jLayeredPane);
        game.requestFocus();
    }
// this is for test use
//    public static void main(String[] args) {
//        JFrame j = new JFrame();
//        Middle m = new Middle(j,panel);
//        j.add(m);
//        m.startPlay();
//        j.setBounds(100,100,768,576);
//        j.setResizable(false);
//        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        j.setVisible(true);
//    }

}
