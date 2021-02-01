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
    Completable insert(Work work);

    @Update
    Completable update(Work work);

    @Delete
    Completable delete(Work work);

    @Query("DELETE FROM Work")
    Completable deleteAll();

}
