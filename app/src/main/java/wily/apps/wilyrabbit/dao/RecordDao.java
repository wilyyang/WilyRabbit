package wily.apps.wilyrabbit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import wily.apps.wilyrabbit.entity.Record;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM Record")
    Flowable<List<Record>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Record record);

    @Update
    Completable update(Record record);

    @Delete
    Completable delete(Record record);

    @Query("DELETE FROM Record")
    Completable deleteAll();

}
