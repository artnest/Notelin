package artnest.notelin

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import org.jetbrains.anko.setContentView

class NoteActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NoteActivityUI().setContentView(this)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, NoteFragment()).commit()
    }
}
