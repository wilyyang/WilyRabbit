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
import wily.apps.wilyrabbit.entity.Work;

@Dao
public interface WorkDao {
    @Query("SELECT * FROM Work")
    Flowable<List<Work>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Work todo);

    @Update
    Completable update(Work todo);

    @Delete
    Completable delete(Work todo);

    @Query("DELETE FROM Work")
    Completable deleteAll();

}
