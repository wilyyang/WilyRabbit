package wily.apps.wilyrabbit.entity;

import android.text.format.DateFormat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Work {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int type;
    private String title;
    private boolean active;

    private int goalCost;
    private int initCost;
    private int perCost;

    public Work(int type, String title, boolean active, int goalCost, int initCost, int perCost) {
        this.type = type;
        this.title = title;
        this.active = active;
        this.goalCost = goalCost;
        this.initCost = initCost;
        this.perCost = perCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getGoalCost() {
        return goalCost;
    }

    public void setGoalCost(int goalCost) {
        this.goalCost = goalCost;
    }

    public int getInitCost() {
        return initCost;
    }

    public void setInitCost(int initCost) {
        this.initCost = initCost;
    }

    public int getPerCost() {
        return perCost;
    }

    public void setPerCost(int perCost) {
        this.perCost = perCost;
    }

    @Override
    public String toString() {
        return "id= " + this.id + " , type= " + this.type + " , title= " + this.title+ " , active= " + this.active+
                " , goalCost= " + this.goalCost+ " , initCost= " + this.initCost+ " , perCost= " + this.perCost;
    }
}
