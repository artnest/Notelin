package artnest.notelin.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.util.List;

import artnest.notelin.App;
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

    public void put(final NoteEntity note) {
        insertTask(note);
    }

    public void put(final NoteEntity note, @Nullable final TaskListener listener) {
        insertTask(note, listener);
    }

    public void put(final List<NoteEntity> noteList) {
        insertAllTask(noteList);
    }

    public void put(final List<NoteEntity> noteList, @Nullable final TaskListener listener) {
        insertAllTask(noteList, listener);
    }

    private void insertTask(final NoteEntity noteEntity) {
        insertTask(noteEntity, null);
    }

    private void insertTask(NoteEntity noteEntity, @Nullable final TaskListener listener) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                noteDao.insert(noteEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (listener != null) {
                    listener.onTaskFinished();
                }
            }
        }.execute(App.getContext());
    }

    private void insertAllTask(final List<NoteEntity> noteEntities) {
        insertAllTask(noteEntities, null);
    }

    private void insertAllTask(final List<NoteEntity> noteEntities, @Nullable final TaskListener listener) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                noteDao.insertAll(noteEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (listener != null) {
                    listener.onTaskFinished();
                }
            }
        }.execute(App.getContext());
    }

    private void deleteTask(final NoteEntity noteEntity) {
        deleteTask(noteEntity, null);
    }

    private void deleteTask(final NoteEntity noteEntity, @Nullable final TaskListener listener) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                noteDao.delete(noteEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (listener != null) {
                    listener.onTaskFinished();
                }
            }
        }.execute(App.getContext());
    }

    private void deleteAllTask() {
        deleteAllTask(null);
    }

    private void deleteAllTask(@Nullable final TaskListener listener) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                noteDao.deleteAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (listener != null) {
                    listener.onTaskFinished();
                }
            }
        }.execute(App.getContext());
    }

    interface TaskListener {
        void onTaskFinished();
    }
}
