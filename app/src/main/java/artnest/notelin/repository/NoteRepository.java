package artnest.notelin.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import artnest.notelin.repository.db.dao.NoteDao;
import artnest.notelin.repository.db.entity.NoteEntity;

/**
 * Created by nesterenko_a on 11.10.2017.
 */

public class NoteRepository {

    private static NoteRepository sInstance;

    private final NoteDao noteDao;

    public static NoteRepository getInstance(NoteDao noteDao) {
        if (sInstance == null) {
            sInstance = new NoteRepository(noteDao);
        }
        return sInstance;
    }

    private NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<NoteEntity> load(long noteId) {
        return noteDao.loadNote(noteId);
    }

    public LiveData<List<NoteEntity>> loadList() {
        return noteDao.loadAll();
    }

    private void insertTask(final NoteEntity noteEntity) {
        // insertTask(noteEntity, null);
    }
}
