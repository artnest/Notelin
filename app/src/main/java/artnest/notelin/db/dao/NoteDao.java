package artnest.notelin.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import artnest.notelin.db.entity.Note;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertNotes(Note... notes);

    @Update
    void updateNotes(Note... notes);

    @Delete
    void delete(Note note);
}
