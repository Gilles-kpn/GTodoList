package fr.gilles.user.task;

import fr.gilles.apiconnect.ApiBase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class TaskView extends JPanel {
    private Task task ;
    private BorderLayout  layout = new BorderLayout();
    private LineBorder border = new LineBorder(Color.black);
    public  TaskView(Task task){
        layout.setHgap(10);
        layout.setVgap(10);
        this.setLayout(layout);
        this.task  = task;
        this.setBorder(border);
        this.setSize(new Dimension(320,200));
        this.setPreferredSize(new Dimension(320,200));
        this.setMaximumSize(new Dimension(320,200));
        this.setMinimumSize(new Dimension(320,200));
        initComponent();
        this.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.WHITE, 5,true);
        this.setBorder(border);
        initPopup();

    }
    private void initComponent(){

        //top bar
        JMenuBar state = new JMenuBar();
        state.setLayout(new BorderLayout());
        state.add(new JLabel(task.getTitle()),BorderLayout.WEST);
        JCheckBox checkBox = new JCheckBox();
        if (task.getDone()){
            checkBox.setSelected(true);
        }else{
            checkBox.setSelected(false);
        }
        checkBox.setEnabled(false);
        state.add(checkBox, BorderLayout.EAST);
        this.add(state,BorderLayout.NORTH);

        //content bar
        JLabel content = new JLabel(task.getContent());
        content.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        content.setHorizontalAlignment(JLabel.CENTER);
        content.setVerticalAlignment(JLabel.CENTER);
        content.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        this.add(scrollPane, BorderLayout.CENTER);

        //status bar
        JMenuBar info =  new JMenuBar();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);
        borderLayout.setHgap(5);
        info.setLayout(borderLayout);
        info.add(new JLabel("Date: "+task.getDate()),BorderLayout.WEST);
        info.add(new JLabel("Duree: "+task.getDuration()+" heures"),BorderLayout.EAST);
        this.add(info, BorderLayout.SOUTH);
    }

    private void initPopup(){
        JPopupMenu popupMenu = new JPopupMenu();
        if (!task.getDone()){
            JMenuItem done  = new JMenuItem("Marquer comme fait");
            done.addActionListener(e -> ApiBase.markTaskDone(task.getId()));
            popupMenu.add(done);
        }else{
            JMenuItem done  = new JMenuItem("Marquer comme non fait");
            done.addActionListener(e -> ApiBase.markTaskUndone(task.getId()));
            popupMenu.add(done);
        }
        JMenuItem update = new JMenuItem("Mettre a jour");

        JMenuItem delete = new JMenuItem("Supprimer la tache");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApiBase.deleteTask(task.getId());
            }
        });
        popupMenu.add(update);
        popupMenu.add(delete);

        this.setComponentPopupMenu(popupMenu);
    }
}
