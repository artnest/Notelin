package artnest.notelin.view.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import artnest.notelin.databinding.ItemNoteListBinding;
import artnest.notelin.repository.db.entity.NoteEntity;
import artnest.notelin.repository.model.NoteModel;

/**
 * Created by nesterenko_a on 12.10.2017.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final List<NoteEntity> dataSet;

    public NoteListAdapter() {
        setHasStableIds(true);
        this.dataSet = new ArrayList<>();
    }

    public void setItems(List<NoteEntity> itemsList) {
        dataSet.clear();
        dataSet.addAll(itemsList);
        notifyDataSetChanged();
    }

    public void add(NoteEntity item) {
        dataSet.add(item);
        notifyItemInserted(dataSet.size() - 1);
    }

    public void add(int position, NoteEntity item) {
        dataSet.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (dataSet.size() < position) {
            return;
        }
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public boolean isEmpty() {
        return dataSet.isEmpty();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNoteListBinding binding = ItemNoteListBinding.inflate(inflater, parent, false);
        return new NoteViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteEntity item = dataSet.get(holder.getAdapterPosition());
        holder.bind(item);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.size() >= position ? dataSet.get(position).getId() : View.NO_ID;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteListBinding binding;

        public NoteViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(NoteEntity item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }

    public interface ClickListener {
        void onItemClicked(NoteModel note);
    }
}
