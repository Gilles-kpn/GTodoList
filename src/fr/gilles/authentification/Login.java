package fr.gilles.authentification;

import fr.gilles.route.Route;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Login extends JPanel implements MouseInputListener {

    private JTextField email ;
    private JPasswordField password;
    public Login(){
        this.setSize(400,400);
        initLayout();
        initLoginScreen();
    }
    private  void initLayout(){
        this.setLayout(new GridBagLayout());
    }
    private void initLoginScreen(){
        JLabel screenName = new JLabel("Connexion");
        JLabel userLabel = new JLabel("Email");
        JLabel passwordLabel  = new JLabel("Mot de passe");
        email = new JTextField(10);
        JLabel gotoRegister = new JLabel("Pas de compte");
        JButton login = new JButton("Me Connecter");
        password = new JPasswordField(10);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        //line 0
        constraints.gridx = 0 ; constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        screenName.setFont(new Font("Dialog",Font.BOLD,15));
        this.add(screenName,constraints);
        //first line
        constraints.gridwidth =1;
        constraints .gridx = 0 ; constraints.gridy = 1;
        this.add(userLabel, constraints);
        constraints.gridx = 1;
        this.add(email, constraints);

        //second Line
        constraints.gridx = 0; constraints.gridy = 2;
        this.add(passwordLabel,constraints);
        constraints.gridx = 1;
        this.add(password,constraints);

        //third line
        constraints.gridx = 0; constraints.gridy = 3;
        login.addMouseListener(this);
        this.add(login,constraints);
        constraints.gridx = 1;

        gotoRegister.setForeground(Color.blue);
        gotoRegister.addMouseListener(this);
        gotoRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(gotoRegister, constraints);
    }


    public static void main(String[] args) {
        JFrame login = new JFrame("Login");
        login.setSize(400,400);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setResizable(false);
        login.add(new Login(),BorderLayout.CENTER);
        login.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //
        switch (e.getSource().getClass().getName()){
            //case button
            case "javax.swing.JButton" ->{
                Route.loginUser(email.getText(),password.getText());
            }
            case "javax.swing.JLabel"->{
                Route.gotoRegister();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
