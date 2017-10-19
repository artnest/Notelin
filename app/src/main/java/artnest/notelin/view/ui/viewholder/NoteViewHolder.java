package artnest.notelin.view.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import artnest.notelin.R;
import artnest.notelin.repository.db.entity.NoteEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by nesterenko_a on 19.10.2017.
 */

public class NoteViewHolder extends FlexibleViewHolder {

    @BindView(R.id.text_name)
    TextView mTextViewName;
    @BindView(R.id.text_text)
    TextView mTextViewText;

    public NoteViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);
    }

    public void bind(NoteEntity item) {
        mTextViewName.setText(item.getName());
        mTextViewText.setText(item.getText());
    }
}
