package fr.gilles.user.task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TasksView extends JPanel {
    GridBagConstraints constraints ;
    GridLayout layout = new GridLayout();

    public TasksView(){
        layout.setColumns(2);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setMinimumSize(new Dimension(800,400));

    }

    public void setTasks(ArrayList<Task> tasks){
        initThisSize(tasks.size());
        this.removeAll();

        for (Task task : tasks){
            addTask(task);
        }
    }

    private void addTask(Task task){
        this.add(new TaskView(task));

    }
    private void initThisSize(int size){

        this.setLayout(layout);
        if (size%this.layout.getColumns() == 0){
            this.layout.setRows(size/this.layout.getColumns());
        }else{
            this.layout.setRows((size/this.layout.getColumns())+1);
        }
        int height = this.layout.getRows()*200 + 20;
        this.setPreferredSize(new Dimension(800,height));
        this.setMinimumSize(new Dimension(800,height));
        this.setMaximumSize(new Dimension(800,height));
        this.setSize(800,height);
    }
}
