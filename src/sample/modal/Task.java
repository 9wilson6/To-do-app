package sample.modal;

import java.sql.Timestamp;

public class Task {
    private Timestamp  datecreated;
    private String task;
    private int userId;
    private String description;
    private int taskId;
    public Task(){}
    public Task(int userId, Timestamp datecreated, String task, String description) {
        this.datecreated = datecreated;
        this.task = task;
        this.description = description;
        this.userId=userId;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }
public int getUserId(){
        return this.userId;
}
    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
