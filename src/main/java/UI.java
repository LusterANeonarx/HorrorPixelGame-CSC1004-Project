import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


//just run this!
public class UI extends JFrame{

    public static void main(String[] args) {
        UI ui = new UI();

    }
    public UI(){


        //get music
        try {
            MusicPlayer uimusic = new MusicPlayer("m.wav",true);
            uimusic.play();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //initialize frame
        setTitle("Horror Pixel Game");
        setBounds(100,100,768,576);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);


        //get the main panel ready
        JPanel panel = new JPanel();
        panel.setBounds(0,0,768,576);
        CardLayout cardlayout = new CardLayout();
        panel.setLayout(cardlayout);


        //initialize different panel
        Home home = new Home(this,panel,cardlayout);
        Middle middle = new Middle(this);
        Register register = new Register(this,panel,cardlayout,middle);
        Login login = new Login(this,panel,cardlayout,middle);


        //add cards on panel
        panel.add(home,"home");
        panel.add(register,"register");
        panel.add(login,"login");
        panel.add(middle,"game");

        //add panel on frame
        getContentPane().add(panel);
        //show the home page
        cardlayout.show(panel,"home");


        setVisible(true);


    }


}
