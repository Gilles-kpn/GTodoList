package fr.gilles.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.gilles.apiconnect.ApiBase;
import fr.gilles.user.task.AddTask;
import fr.gilles.user.task.Task;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String name;
    private String email;
    private int id;
    private AddTask addTask;

    public ArrayList<Task> getTasks() {
        initTasks();
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private ArrayList<Task> tasks;

    public User(JsonObject user){
        name = user.get("name").getAsString();
        email = user.get("email").getAsString();
        id = user.get("id").getAsInt();
        initTasks();
        addTask = new AddTask();
    }

    private void initTasks(){
        tasks = new ArrayList<>();
        JsonArray result = Objects.requireNonNull(ApiBase.getTaskAbout(id));
        for (var tmp: result ) {
            tasks.add(new Task(tmp.getAsJsonObject()));
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void addNewTask(){
       String data = addTask.showDialog();
      ApiBase.addNewTask(this.id, data);
    }
    public void search(){

    }

    public void setEmail(String email) {
        this.email = email;
    }
}
