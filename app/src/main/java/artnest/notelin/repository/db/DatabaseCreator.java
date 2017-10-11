package artnest.notelin.repository.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import artnest.notelin.App;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

public class DatabaseCreator {

    private static DatabaseCreator sInstance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private AppDatabase mDb;

    private final AtomicBoolean mInitializing = new AtomicBoolean(true);

    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance() {
        return getInstance(App.getInstance());
    }

    public synchronized static DatabaseCreator getInstance(Application application) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new DatabaseCreator(application);
                }
            }
        }
        return sInstance;
    }

    public DatabaseCreator(Application application) {
        createDb(application);
    }

    public LiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    @Nullable
    public AppDatabase getDatabase() {
        return mDb;
    }

    private void createDb(Context context) {

        Log.d("DatabaseCreator", "Creating DB from " + Thread.currentThread().getName());

        if (!mInitializing.compareAndSet(true, false)) {
            return; // Already initializing
        }

        mIsDatabaseCreated.setValue(false); // Trigger an update to show a loading screen
        new AsyncTask<Context, Void, AppDatabase>() {
            @Override
            protected AppDatabase doInBackground(Context... params) {
                Log.d("DatabaseCreator", "Starting bg job" + Thread.currentThread().getName());

                Context context = params[0].getApplicationContext();
                return Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME).build();
            }

            @Override
            protected void onPostExecute(AppDatabase appDatabase) {
                super.onPostExecute(appDatabase);
                mDb = appDatabase;

                // Now on main thread, notify observers that the db is created and ready
                mIsDatabaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }
}
