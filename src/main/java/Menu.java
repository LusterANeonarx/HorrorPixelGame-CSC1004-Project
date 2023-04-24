import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu extends JPanel {
    Menu(JFrame f,Middle middle){
        setLayout(null);
        setBounds(590,20,150,150);

        JLabel pause = new JLabel("PAUSE");


        JButton restart = new JButton("restart");
//        JButton home = new JButton("home");

        restart.setBounds(25,50,100,20);
//        home.setBounds(25,90,100,20);
        pause.setBounds(50,-20,100,100);
        add(pause);
        add(restart);
//        add(home);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                middle.menuclick = true;

            }
        });
        // below are the function that hasn't done.
//        home.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(panel,"home");
//            }
//        });

    }
}
