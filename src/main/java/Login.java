import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login extends JPanel {// just login
    jdbc connector = new jdbc();
    public Login(JFrame f, JPanel panel, CardLayout cardlayout, Middle middle){
        setLayout(null);
        setBounds(0,0,768,576);

        String path = System.getProperty("user.dir")+"/src/main/java/images/";
        Icon imgIcon = new ImageIcon(path+"cover.gif");

        //add buttons and texts
        JLabel gif = new JLabel(imgIcon);
        JLabel _id = new JLabel("your id:");
        JLabel _pw = new JLabel("your password:");
        JTextField id = new JTextField();
        JTextField pw = new JTextField();
        JButton bt = new JButton("confirm");
        JButton exit = new JButton("return");
        JLabel error = new JLabel("");

        _id.setForeground(Color.WHITE);
        _pw.setForeground(Color.WHITE);
        error.setForeground(Color.WHITE);

        gif.setBounds(0,0,768,576);
        _id.setBounds(270,120,100,100);
        _pw.setBounds(225,160,100,100);
        id.setBounds(334,160,100,20);
        pw.setBounds(334,200,100,20);
        bt.setBounds(284,288,80,20);
        exit.setBounds(384,288,80,20);
        error.setBounds(270,270,1000,100);

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _id = id.getText();
                String _pw = pw.getText();
                connector.initConnection();
                if(_id.equals("")||_pw.equals("")){
                    error.setText("id or password unfulfilled");
                }
                else if (connector.searchForIdPw(_id,_pw)){//does the id exist
                    cardlayout.show(panel,"game");
//                    setFocusable(false);
//                    game.setFocusable(true);
                    f.repaint();
                    middle.requestFocus();
                    middle.startPlay();

                }
                else{
                    error.setText("id or password incorrect");
                }
                connector.closeConnection();


            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.setText("");
                cardlayout.show(panel,"home");
            }
        });
        add(_id);
        add(_pw);
        add(id);
        add(pw);
        add(bt);
        add(exit);
        add(error);
        add(gif);
    }
}
