package artnest.notelin.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String name;

    private String text;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
