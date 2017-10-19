package artnest.notelin.view.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import artnest.notelin.R;
import artnest.notelin.databinding.ActivityNoteListBinding;
import artnest.notelin.repository.db.entity.NoteEntity;
import artnest.notelin.repository.model.NoteModel;
import artnest.notelin.view.ui.adapter.NoteListAdapter;
import artnest.notelin.view.ui.dialog.CustomDialogs;
import artnest.notelin.viewmodel.NoteListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;

public class NoteListActivity extends AppCompatActivity implements NoteListAdapter.ClickListener {

    private NoteListViewModel mViewModel;

    // private NoteListAdapter mNoteListAdapter;
    private FlexibleAdapter<NoteEntity> mNoteListAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_note_list);
        ActivityNoteListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);
        binding.content.setView(this);
        initViews();
        initViewModel();
    }

    private void initViews() {
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        /*FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        mRecyclerView.setLayoutManager(layoutManager);*/
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //     mNoteListAdapter = new NoteListAdapter();
        mNoteListAdapter = new FlexibleAdapter<>(null);
        mRecyclerView.setAdapter(mNoteListAdapter);
        mRecyclerView.addOnScrollListener(new FloatingActionButtonScrollListener(mFab));
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
        // mNoteListAdapter.setItems(noteEntities);
        mNoteListAdapter.clear();
        mNoteListAdapter.addItems(0, noteEntities);
    }

    private void showProgress() {
    }

    private void hideProgress() {
    }

    @Override
    public void onItemClicked(NoteModel note) {
        mViewModel.onListItemClicked((NoteEntity) note);
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

    public void onFabButtonClicked() {
        CustomDialogs.showAddNewNoteAlertDialog(this,
                (dialog, which) -> mViewModel.onFabButtonClicked());
    }
}
