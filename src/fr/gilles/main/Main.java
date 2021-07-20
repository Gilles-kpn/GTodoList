package fr.gilles.main;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import fr.gilles.authentification.Login;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame frame ;
    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
        UIManager.setLookAndFeel(FlatLightLaf.class.getName());
        frame = new JFrame();
        gotoRoute(new Login(),"Login" ,new Dimension(400,400),false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void gotoRoute(JPanel route,String name, Dimension dimension,boolean resizable){
        frame.setTitle(name);
        frame.setContentPane(route);
        frame.setResizable(resizable);
        frame.setPreferredSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setLocationRelativeTo(null);
    }
    public static JFrame  getMainFrame(){
        return frame;
    }
}
