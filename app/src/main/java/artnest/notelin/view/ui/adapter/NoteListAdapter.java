package artnest.notelin.view.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import artnest.notelin.R;
import artnest.notelin.repository.db.entity.NoteEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nesterenko_a on 12.10.2017.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final ClickListener mListener;
    private final List<NoteEntity> dataSet;

    public NoteListAdapter(ClickListener listener) {
        setHasStableIds(true);
        this.mListener = listener;
        this.dataSet = new ArrayList<>();
    }

    public void setItems(List<NoteEntity> itemsList) {
        dataSet.clear();
        dataSet.addAll(itemsList);
        notifyDataSetChanged();
    }

    public void add(NoteEntity item) {
        add(null, item);
    }

    public void add(@Nullable Integer position, NoteEntity item) {
        if (position != null) {
            dataSet.add(position, item);
            notifyItemInserted(position);
        } else {
            dataSet.add(item);
            notifyItemInserted(dataSet.size() - 1);
        }
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteEntity item = dataSet.get(position);
        holder.name.setText(item.getName());
        holder.text.setText(item.getText());
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

        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_text)
        TextView text;

        public NoteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.tv_text)
        public void onItemClick() {
            if (mListener != null) {
                mListener.onItemClicked(dataSet.get(getAdapterPosition()));
            }
        }
    }

    public interface ClickListener {
        void onItemClicked(NoteEntity note);
    }
}
