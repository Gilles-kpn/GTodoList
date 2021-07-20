package fr.gilles.user.task;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends JDialog implements MouseInputListener {
    JTextField  day = new JTextField("00-31");
    JTextField month = new JTextField("00-12");
    JTextField year = new JTextField("2021-");
    JTextField hour = new JTextField("00-24");
    JTextField min = new JTextField("00-60");
    JTextArea content = new JTextArea();
    JTextField title = new JTextField(20);
    JTextField  duration = new JTextField(3);
    String result = "";

    public AddTask(){
        this.setSize(400,600);
        this.setResizable(false);
        initTaskDialog();
        this.setModal(true);
        this.setLocationRelativeTo(null);
    }

    private void initTaskDialog(){
        this.setLayout(new GridBagLayout());
        initComponent();
    }
    private void initComponent(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridwidth = 350;
        constraints.gridx = 0; constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JLabel name = new JLabel("Ajouter une tache");
        name.setFont(new Font("Dialog",Font.BOLD,20));
        this.add(name, constraints);

        JLabel titleLabel = new JLabel("Titre de la tache");
        constraints.gridy = 1;
        this.add(titleLabel, constraints);

        constraints.gridy = 2;

        this.add(title,constraints);
        JLabel dateLabel = new JLabel("Date de la tache - jj mm aaaa hh min");
        constraints.gridy = 3;
        this.add(dateLabel, constraints);
        constraints.gridy = 4;
        JMenuBar date = new JMenuBar();

        date.add(day);
        date.add(month);
        date.add(year);
        date.add(hour);
        date.add(min);

        this.add(date,constraints);
        JLabel contentLabel = new JLabel("Contenu de la tache");
        constraints.gridy = 5;
        this.add(contentLabel, constraints);

        constraints.gridy = 6;
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(content);
        content.setLineWrap(true);
        scrollPane.setMaximumSize(new Dimension(350,200));
        scrollPane.setSize(350,200);
        constraints.ipady = 100;
        this.add(scrollPane,constraints);

        JLabel durationLabel = new JLabel("Duree de la tache en heure");
        constraints.gridy = 7;constraints.ipady = 0;

        this.add(durationLabel, constraints);

        constraints.gridy = 8;
        this.add(duration,constraints);

        constraints.gridy = 9;
        JButton register = new JButton("Enregistrer");
        register.addMouseListener(this);
        this.add(register, constraints);

    }

    public String showDialog(){
        this.setVisible(true);
        return  result;
    }

    private String getDate(){
        return Integer.parseInt(year.getText())+"-"+Integer.parseInt(month.getText())+"-"+Integer.parseInt(day.getText())+" "+Integer.parseInt(hour.getText())+":"+Integer.parseInt(min.getText())+":00";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Map<String,String> arguments = new HashMap<>();
        arguments.put("title",title.getText());
        arguments.put("duration",duration.getText());
        arguments.put("content",content.getText());
        arguments.put("date",getDate());
        result = new Gson().toJson(arguments);
        this.setVisible(false);
        this.dispose();
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
