package wily.apps.wilyrabbit.entity;

import android.text.format.DateFormat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Work {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    private int hour;
    private int minute;

    public Work(String title, int hour, int minute) {
        this.title = title; this.hour = hour; this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTimeFormat(){
        return ""+hour+" : "+minute;
    }

    @Override
    public String toString() {
        return "\n id=> " + this.id + " , title=> " + this.title+" , time=> "+hour+" : "+minute;
    }
}
