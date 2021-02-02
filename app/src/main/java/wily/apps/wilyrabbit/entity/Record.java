package wily.apps.wilyrabbit.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int type;
    private String title;
    private boolean status;

    private int hour;
    private int minute;
    private int second;

    public Record(int type, String title, boolean status, int hour, int minute, int second) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "id= " + this.id + " , type= " + this.type + " , title= " + this.title+ " , status= " + this.status+
                " , hour= " + this.hour+ " , minute= " + this.minute+ " , second= " + this.second;
    }
}
