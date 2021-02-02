package wily.apps.wilyrabbit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import wily.apps.wilyrabbit.dao.RecordDao;
import wily.apps.wilyrabbit.entity.Record;

@Database(entities = {Record.class}, version =  1)
public abstract class RecordDatabase extends RoomDatabase {
    public abstract RecordDao recordDao();

    private static RecordDatabase INSTANCE = null;
    private static String DB_NAME = "RECORD_DB";

    public static RecordDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, RecordDatabase.class , "record-db")
                    .build();
        }
        return  INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}