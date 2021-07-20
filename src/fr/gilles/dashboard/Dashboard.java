package fr.gilles.dashboard;

import fr.gilles.authentification.Login;
import fr.gilles.user.User;
import fr.gilles.user.task.TasksView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Dashboard extends JPanel implements MouseInputListener {

    private User user = null;
    private JLabel tasksSize = new JLabel("0 taches");
    private TasksView tasksView  = new TasksView();
    public Dashboard(User user){
        this.user = user;
        this.setMinimumSize(new Dimension(600,400));
        this.setLayout(new BorderLayout());
        initComponent();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initComponent(){
        //
        JMenuBar menuBar = new JMenuBar();
        JButton add = new JButton("+");
        add.setName("AddTask");
        add.addMouseListener(this);
        //
        JLabel userLabel = new JLabel(this.user.getName());
        //
        JButton searchTask = new JButton("Rechercher");
        searchTask.setName("Search");
        searchTask.addMouseListener(this);
        JTextField search = new JTextField();
        //
        JButton deconnect = new JButton("Se Deconnecter");
        deconnect.setName("Deconnection");
        deconnect.addMouseListener(this);
        load();
        JMenuBar statusBar = new JMenuBar();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(5);
        borderLayout.setVgap(5);
        statusBar.setLayout(borderLayout);
        statusBar.add(tasksSize,BorderLayout.CENTER);
        statusBar.add(deconnect,BorderLayout.EAST);
        statusBar.add(userLabel,BorderLayout.WEST);
        statusBar.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        //
        search.setBorder( new EmptyBorder( 3, 3, 3, 3 ) );
        search.setMargin(new Insets(1,5,1,5));
        search.setText("Rechercher une tache");

        //search
        BorderLayout searchBorderLayout = new BorderLayout();
        searchBorderLayout.setVgap(5);
        searchBorderLayout.setHgap(5);
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(searchBorderLayout);
        searchPanel.add(search, BorderLayout.CENTER);
        searchPanel.add(searchTask,BorderLayout.EAST);

        //user

        //
        menuBar.setLayout(new BorderLayout());
        menuBar.add(add,BorderLayout.WEST);
        menuBar.add(searchPanel,BorderLayout.CENTER);
        menuBar.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
       // menuBar.add(user, BorderLayout.EAST);
        //

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tasksView);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.setPreferredSize(new Dimension(800,400));
        //
        this.add(menuBar,BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getSource().getClass().getName()){
            case "javax.swing.JButton"->{
                switch (((JButton)e.getSource()).getName()){
                    case "AddTask"->{
                        user.addNewTask();
                        load();
                    }
                    case "Deconnect"->{

                    }
                    case "Search"->{
                        user.search();
                    }
                }
            }
            case "javax.swing.JLabel"->{

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
    private void load(){
        tasksSize.setText(user.getTasks().size()+" Taches");
        tasksView.setTasks(user.getTasks());
    }
    public void reload(){
        load();
    }
}
