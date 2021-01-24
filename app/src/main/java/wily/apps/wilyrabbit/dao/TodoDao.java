package wily.apps.wilyrabbit.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import wily.apps.wilyrabbit.entity.Todo;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM Todo")
    Flowable<List<Todo>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Todo todo);

    @Update
    Completable update(Todo todo);

    @Delete
    Completable delete(Todo todo);

    @Query("DELETE FROM Todo")
    Completable deleteAll();

}
