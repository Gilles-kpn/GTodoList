package fr.gilles.user.task;

import com.google.gson.JsonObject;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task {

    private String title;
    private String content;
    private Date date;
    private int duration ;
    private Boolean done;
    private int id;
    private int user_id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Task(String title, String content, Date date, int duration, Boolean done) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.duration = duration;
        this.done = done;
    }

    public Task(JsonObject task){
        this.title = task.get("title").getAsString();
        this.content = task.get("content").getAsString();
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(task.get("date").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.duration = task.get("duration").getAsInt();
        this.done = task.get("done").getAsInt() == 1;
        this.id = task.get("id").getAsInt();
        this.user_id = task.get("user_id").getAsInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
