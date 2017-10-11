package artnest.notelin.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import artnest.notelin.repository.db.dao.NoteDao;
import artnest.notelin.repository.db.entity.NoteEntity;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notelin-db";

    public abstract NoteDao noteDao();
}
