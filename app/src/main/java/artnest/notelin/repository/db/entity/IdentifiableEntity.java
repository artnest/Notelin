package artnest.notelin.repository.db.entity;

import android.arch.persistence.room.PrimaryKey;

import artnest.notelin.repository.model.IdentifiableModel;

/**
 * Created by nesterenko_a on 11.10.2017.
 */

abstract class IdentifiableEntity implements IdentifiableModel {

    @PrimaryKey(autoGenerate = true)
    protected long id;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
