package artnest.notelin.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import artnest.notelin.repository.db.dao.NoteDao;
import artnest.notelin.repository.db.entity.NoteEntity;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public void save(@NonNull final NoteEntity note) {
        Observable
                .just(note)
                .doOnNext(noteDao::insert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void save(@NonNull final NoteEntity note, @NonNull final TaskListener listener) {
        Observable
                .just(note)
                .doOnNext(noteDao::insert)
                // .doOnNext(noteDao::insertAll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(listener::onTaskFinished)
                .subscribe();
    }

    public void save(final List<NoteEntity> noteList) {
        Observable
                .fromIterable(noteList)
                .doOnNext(noteDao::insert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void save(@NonNull final List<NoteEntity> noteList, @NonNull final TaskListener listener) {
        Observable
                .fromIterable(noteList)
                .doOnNext(noteDao::insert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(listener::onTaskFinished)
                .subscribe();
    }

    public void remove(@NonNull final NoteEntity note) {
        Observable
                .just(note)
                .doOnNext(noteDao::delete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void remove(@NonNull final NoteEntity note, @NonNull final TaskListener listener) {
        Observable
                .just(note)
                .doOnNext(noteDao::delete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(listener::onTaskFinished)
                .subscribe();
    }

    public void removeAll() {
        Completable
                .fromAction(noteDao::deleteAll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void removeAll(@NonNull final TaskListener listener) {
        Completable
                .fromAction(noteDao::deleteAll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(listener::onTaskFinished)
                .subscribe();
    }

    public interface TaskListener {
        void onTaskFinished();
    }
}
