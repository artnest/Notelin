package artnest.notelin.repository.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import artnest.notelin.repository.db.entity.NoteEntity;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity... notes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntities);

    @Update
    void update(NoteEntity... notes);

    @Update
    void updateAll(List<NoteEntity> noteEntities);

    @Delete
    void delete(NoteEntity... notes);

    @Delete
    void deleteAll(List<NoteEntity> noteEntities);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("SELECT * FROM notes")
    LiveData<List<NoteEntity>> loadAll();

    @Query("SELECT * FROM notes WHERE id = :noteId")
    LiveData<NoteEntity> loadNote(long noteId);

    @Query("SELECT * FROM notes where id IN (:noteIds)")
    LiveData<List<NoteEntity>> loadByIds(long[] noteIds);
}
