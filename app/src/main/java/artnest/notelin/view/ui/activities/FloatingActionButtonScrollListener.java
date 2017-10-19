package artnest.notelin.view.ui.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nesterenko_a on 19.10.2017.
 */

public class FloatingActionButtonScrollListener extends RecyclerView.OnScrollListener {

    private FloatingActionButton mFab;

    public FloatingActionButtonScrollListener(FloatingActionButton fab) {
        this.mFab = fab;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0 && mFab.getVisibility() == View.VISIBLE) {
            mFab.hide();
        } else if (dy < 0 && mFab.getVisibility() != View.VISIBLE) {
            mFab.show();
        }
    }
}
