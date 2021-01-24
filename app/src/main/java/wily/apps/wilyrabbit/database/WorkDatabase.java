package wily.apps.wilyrabbit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import wily.apps.wilyrabbit.dao.WorkDao;
import wily.apps.wilyrabbit.entity.Work;

@Database(entities = {Work.class}, version =  1)
public abstract class WorkDatabase extends RoomDatabase {
    public abstract WorkDao workDao();

    private static WorkDatabase INSTANCE = null;
    private static String DB_NAME = "WORK_DB";

    //디비객체생성 가져오기
    public static WorkDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, WorkDatabase.class , "work-db")
                    .build();
        }
        return  INSTANCE;
    }

    //디비객체제거
    public static void destroyInstance() {
        INSTANCE = null;
    }
}