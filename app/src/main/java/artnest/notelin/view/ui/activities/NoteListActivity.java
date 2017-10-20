package artnest.notelin.view.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import artnest.notelin.App;
import artnest.notelin.R;
import artnest.notelin.databinding.ActivityNoteListBinding;
import artnest.notelin.repository.NoteRepository;
import artnest.notelin.repository.db.DatabaseCreator;
import artnest.notelin.repository.db.entity.NoteEntity;
import artnest.notelin.repository.model.NoteModel;
import artnest.notelin.view.ui.adapter.NoteListAdapter;
import artnest.notelin.view.ui.dialog.CustomDialogs;
import artnest.notelin.viewmodel.NoteListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;

public class NoteListActivity extends AppCompatActivity implements NoteListAdapter.ClickListener,
        FlexibleAdapter.OnItemLongClickListener {

    private NoteListViewModel mViewModel;
    // private NoteListAdapter mNoteListAdapter;
    private FlexibleAdapter<NoteEntity> mNoteListAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
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
        initToolbar();
        setupDrawerContent(mNavView);
        initRecyclerView();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        /*FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        mRecyclerView.setLayoutManager(layoutManager);*/
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //     mNoteListAdapter = new NoteListAdapter();
        mNoteListAdapter = new FlexibleAdapter<>(null, this, true);
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
    public void onItemLongClick(int position) {
        NoteRepository.getInstance(DatabaseCreator.getInstance()
                .getDatabase().noteDao()).remove(mNoteListAdapter.getItem(position),
                () -> Toast.makeText(App.getContext(), "Note deleted", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
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
