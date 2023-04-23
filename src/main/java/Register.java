import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Register extends JPanel{
    jdbc connector = new jdbc();
    public Register(JFrame f, JPanel panel, CardLayout cardlayout, Middle middle){
        setLayout(null);
        setBounds(0,0,768,576);

        String path = System.getProperty("user.dir")+"/src/main/java/images/";
        Icon imgIcon = new ImageIcon(path+"cover.gif");

        JLabel gif = new JLabel(imgIcon);
        JTextField id = new JTextField();
        JLabel _id = new JLabel("new id:");
        JLabel _pw = new JLabel("new password:");
        JTextField pw = new JTextField();
        JLabel _age = new JLabel("your name:");
        JTextField age = new JTextField();
        JLabel error = new JLabel("warning, confirm will not be validate with improper input.");
        JButton bt = new JButton("confirm");
        JButton exit = new JButton("return");

        _id.setForeground(Color.WHITE);
        _pw.setForeground(Color.WHITE);
        _age.setForeground(Color.WHITE);
        error.setForeground(Color.WHITE);

        gif.setBounds(0,0,768,576);
        _id.setBounds(270,120,100,100);
        _pw.setBounds(225,160,100,100);
        _age.setBounds(225,200,100,100);
        id.setBounds(334,160,100,20);
        pw.setBounds(334,200,100,20);
        age.setBounds(334,240,100,20);
        bt.setBounds(284,288,80,20);
        exit.setBounds(384,288,80,20);
        error.setBounds(200,300,1000,100);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _id = id.getText();
                String _pw = pw.getText();
                String _age = age.getText();
                //connect with the jdbc to show if it meets the register standard
                connector.initConnection();
                if (!connector.searchForId(_id)){//does the id exist
                    connector.add(_id,_pw,_age);
                    cardlayout.show(panel,"game");

                    f.repaint();
                    middle.requestFocus();
                    middle.startPlay();

                }
                connector.closeConnection();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(panel,"home");
            }
        });
        add(id);
        add(pw);
        add(bt);
        add(exit);
        add(_id);
        add(_pw);
        add(_age);
        add(age);
        add(error);
        add(gif);

    }

    public void paintComponent(Graphics g){
        BufferedImage image;
        String path = System.getProperty("user.dir")+"/src/main/java/images/";
        try {
            image = ImageIO.read(new File(path+"cover.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(image,0,0,768,576,this);
    }
}
