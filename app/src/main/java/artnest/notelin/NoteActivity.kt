package artnest.notelin

import android.os.Bundle
import android.support.v4.app.FragmentActivity

class NoteActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}
