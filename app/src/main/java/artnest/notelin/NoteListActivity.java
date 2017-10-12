package artnest.notelin;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import artnest.notelin.repository.db.entity.NoteEntity;
import artnest.notelin.view.ui.adapter.NoteListAdapter;
import artnest.notelin.view.ui.dialog.CustomDialogs;
import artnest.notelin.viewmodel.NoteListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity implements NoteListAdapter.ClickListener {

    private NoteListViewModel mViewModel;

    private NoteListAdapter mNoteListAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        initViews();
        initViewModel();
    }

    private void initViews() {
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mNoteListAdapter = new NoteListAdapter(this);
        mRecyclerView.setAdapter(mNoteListAdapter);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        subscribeDataStreams(mViewModel);
    }

    private void subscribeDataStreams(NoteListViewModel viewModel) {
        viewModel.getNoteList().observe(this,
                noteEntities -> {
                    if (noteEntities != null) {
                        showNoteListInUi(noteEntities);
                    } else {
                        // showProgress();
                    }
                });

        viewModel.getSelectedItem().observe(this,
                noteEntity -> {
                    if (noteEntity != null) {
                        // hideProgress();
                    }
                });
    }

    private void showNoteListInUi(List<NoteEntity> noteEntities) {
        mNoteListAdapter.setItems(noteEntities);
    }

    private void showProgress() {
    }

    private void hideProgress() {
    }

    @Override
    public void onItemClicked(NoteEntity note) {
        mViewModel.onListItemClicked(note);
        // openNoteDetailActivity(note);
    }

    private void openNoteDetailActivity() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                CustomDialogs.showAddNewNoteAlertDialog(this,
                        (dialog, which) -> {
                            mViewModel.onFabButtonClicked();
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
