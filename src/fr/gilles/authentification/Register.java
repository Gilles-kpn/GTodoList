package fr.gilles.authentification;

import fr.gilles.route.Route;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Register extends JPanel implements MouseInputListener {

    private JTextField  name;
    private JTextField email ;
    private JPasswordField password;
    public Register(){
        this.setSize(400,400);
        initLayout();
        initRegisterScreen();
    }
    private  void initLayout(){
        this.setLayout(new GridBagLayout());
    }
    private void initRegisterScreen(){
        JLabel nameLabel = new JLabel("Nom");
        name = new JTextField(10);
        JLabel screenName = new JLabel("Inscription");
        JLabel userLabel = new JLabel("Email");
        JLabel passwordLabel  = new JLabel("Mot de passe");
        email = new JTextField(10);
        JLabel gotoLogin = new JLabel("Deja un compte");
        JButton register = new JButton("M'Enregistrer");
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
        this.add(nameLabel, constraints);
        constraints.gridx = 1;
        this.add(name, constraints);

        //second Line
        constraints.gridx = 0; constraints.gridy = 2;
        this.add(userLabel,constraints);
        constraints.gridx = 1;
        this.add(email,constraints);

        //thirdLine
        constraints.gridx = 0; constraints.gridy = 3;
        this.add(passwordLabel,constraints);
        constraints.gridx = 1;
        this.add(password,constraints);

        //third line
        constraints.gridx = 0; constraints.gridy = 4;
        register.addMouseListener(this);
        this.add(register,constraints);
        constraints.gridx = 1;

        gotoLogin.setForeground(Color.blue);
        gotoLogin.addMouseListener(this);
        gotoLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(gotoLogin, constraints);
    }


    public static void main(String[] args) {
        JFrame login = new JFrame("Register");
        login.setSize(400,400);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setResizable(false);
        login.add(new Register(),BorderLayout.CENTER);
        login.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //
        switch (e.getSource().getClass().getName()){
            //case button
            case "javax.swing.JButton" ->{
                Route.registerUser(name.getText(),email.getText(),password.getText());
            }
            case "javax.swing.JLabel" ->{
                Route.gotoLogin();
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
