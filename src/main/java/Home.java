
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel{
    public Home(JFrame f,JPanel panel,CardLayout cardlayout){
        //initialize home
        setLayout(null);
        setBounds(0,0,768,576);


        //get the cover
        String path = System.getProperty("user.dir")+"/src/main/java/images/";
        Icon imgIcon = new ImageIcon(path+"cover.gif");
        JLabel gif = new JLabel(imgIcon);


        //initialize button
        JButton register_bt = new JButton("Register");
        JButton login_bt = new JButton("Login");


        //get button position
        gif.setBounds(0,0,768,576);
        register_bt.setBounds(334,200,100,40);
        login_bt.setBounds(334,300,100,40);

        register_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // when clicked register, change into register card
                cardlayout.show(panel,"register");
                repaint();
            }
        });

        login_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(panel,"login");
                repaint();
            }
        });


        //add buttons
        add(register_bt);
        add(login_bt);
        add(gif);

    }

}
