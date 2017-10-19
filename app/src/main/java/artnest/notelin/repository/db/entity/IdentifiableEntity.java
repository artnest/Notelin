package artnest.notelin.repository.db.entity;

import android.arch.persistence.room.PrimaryKey;

import artnest.notelin.repository.model.IdentifiableModel;
import artnest.notelin.view.ui.viewholder.NoteViewHolder;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

/**
 * Created by nesterenko_a on 11.10.2017.
 */

// FIXME: 19.10.2017 Should be somehow decoupled from NoteViewHolder
abstract class IdentifiableEntity extends AbstractFlexibleItem<NoteViewHolder> implements IdentifiableModel {

    @PrimaryKey(autoGenerate = true)
    protected long id;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentifiableEntity)) return false;
        IdentifiableEntity that = (IdentifiableEntity) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
