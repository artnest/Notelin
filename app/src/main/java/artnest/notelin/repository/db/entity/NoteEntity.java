package artnest.notelin.repository.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import artnest.notelin.repository.model.NoteModel;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Entity(tableName = "notes")
public class NoteEntity extends IdentifiableEntity implements NoteModel {

    private String name;

    private String text;

    public NoteEntity() {
    }

    @Ignore
    public NoteEntity(long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    @Ignore
    public NoteEntity(NoteModel note) {
        this.id = note.getId();
        this.name = note.getName();
        this.text = note.getText();
    }

    @Override
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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // if (o == null || getClass() != o.getClass()) return false;
        if (o == null || !(o instanceof NoteEntity)) return false;
        NoteEntity that = (NoteEntity) o;
        return name.equals(that.name) && text.equals(that.text);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteEntity)) return false;
        NoteEntity that = (NoteEntity) o;
        return this.id == that.id;
    }

    /*@Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }*/

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void update(NoteEntity note) {
        this.name = note.getName();
        this.text = note.getText();
    }
}
