package artnest.notelin.repository.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.view.View;

import java.util.List;

import artnest.notelin.R;
import artnest.notelin.repository.model.NoteModel;
import artnest.notelin.view.ui.viewholder.NoteViewHolder;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

/**
 * Created by nesterenko_a on 10.10.2017.
 */

@Entity(tableName = "notes")
public class NoteEntity extends AbstractFlexibleItem<NoteViewHolder> implements NoteModel {

    @PrimaryKey(autoGenerate = true)
    private long id;
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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    /*@Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteModel)) return false;
        NoteModel that = (NoteModel) o;
        return this.id == that.getId();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void update(NoteEntity note) {
        this.name = note.getName();
        this.text = note.getText();
    }


    @Override
    public NoteViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new NoteViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, NoteViewHolder holder, int position, List payloads) {
        holder.bind(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_note_list;
    }
}
