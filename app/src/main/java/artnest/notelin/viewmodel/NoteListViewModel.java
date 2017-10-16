package artnest.notelin.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

import artnest.notelin.repository.NoteRepository;
import artnest.notelin.repository.db.AppDatabase;
import artnest.notelin.repository.db.DatabaseCreator;
import artnest.notelin.repository.db.entity.NoteEntity;

/**
 * Created by nesterenko_a on 12.10.2017.
 */

public class NoteListViewModel extends ViewModel {

    private NoteRepository mNoteRepository;

    private MutableLiveData<NoteEntity> mSelectedItem;
    private LiveData<List<NoteEntity>> mNoteList;

    public NoteListViewModel() {
        this(null);
    }

    public NoteListViewModel(@Nullable NoteRepository noteRepository) {
        if (this.mNoteRepository != null) {
            return;
        }
        if (noteRepository != null) {
            this.mNoteRepository = noteRepository;
        }
    }

    public void onFabButtonClicked() {
        // DatabaseMockUtils.populateMockDataAsync(DatabaseCreator.getInstance().getDatabase());

        // NoteRepository.getInstance(DatabaseCreator.getInstance().getDatabase().noteDao()).removeAll();
        AppDatabase mDb = DatabaseCreator.getInstance().getDatabase();
        List<NoteEntity> mNoteEntities = new LinkedList<>();
        mNoteEntities.add(new NoteEntity(1, "aaa", "test"));
        mNoteEntities.add(new NoteEntity(2, "bbb", "test"));
        mNoteEntities.add(new NoteEntity(3, "ccc", "test"));
        mNoteEntities.add(new NoteEntity(4, "ddd", "test"));
        mNoteEntities.add(new NoteEntity(5, "eee", "test"));
        NoteRepository.getInstance(mDb.noteDao()).save(mNoteEntities);
    }

    public LiveData<List<NoteEntity>> getNoteList() {
        if (mNoteList == null) {
            mNoteList = new MutableLiveData<>();
            loadNotes();
        }
        return mNoteList;
    }

    public void onListItemClicked(NoteEntity note) {
        if (mSelectedItem.getValue() == note) {
            return;
        }
        mSelectedItem.postValue(note);
    }

    public LiveData<NoteEntity> getSelectedItem() {
        if (mSelectedItem == null) {
            mSelectedItem = new MutableLiveData<>();
        }
        return mSelectedItem;
    }

    private void loadNotes() {
        mNoteList = Transformations.switchMap(DatabaseCreator.getInstance().isDatabaseCreated(),
                isDbCreated -> {
                    if (isDbCreated) {
                        onDatabaseCreated();
                        return mNoteRepository.loadList();
                    }
                    return null;
                });
    }

    // FIXME: use dagger to inject dependency instances
    private void onDatabaseCreated() {
        if (mNoteRepository == null) {
            //noinspection ConstantConditions
            mNoteRepository = NoteRepository.getInstance(DatabaseCreator.getInstance().getDatabase().noteDao());
        }
    }
}
