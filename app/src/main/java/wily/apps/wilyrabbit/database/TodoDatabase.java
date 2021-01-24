package wily.apps.wilyrabbit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import wily.apps.wilyrabbit.dao.TodoDao;
import wily.apps.wilyrabbit.entity.Todo;

@Database(entities = {Todo.class}, version =  1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

    private static TodoDatabase INSTANCE = null;
    private static String DB_NAME = "RECORD_DB";

    //디비객체생성 가져오기
    public static TodoDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, TodoDatabase.class , "todo-db")
                    .build();
        }
        return  INSTANCE;
    }

    //디비객체제거
    public static void destroyInstance() {
        INSTANCE = null;
    }
}