package artnest.notelin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import artnest.notelin.ui.dialogs.CustomDialogs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                            String task = String.valueOf("12345");
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
