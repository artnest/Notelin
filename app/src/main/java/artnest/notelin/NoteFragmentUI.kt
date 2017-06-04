package artnest.notelin

import android.widget.EditText
import org.jetbrains.anko.*

class NoteFragmentUI : AnkoComponent<NoteFragment> {

    lateinit var title : EditText

    override fun createView(ui: AnkoContext<NoteFragment>) = with(ui) {
        verticalLayout {
            title = editText {
                hintResource = R.string.note_title_hint
            }
        }
    }
}