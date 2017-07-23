package artnest.notelin.view

import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.EditText
import artnest.notelin.R
import artnest.notelin.controller.NoteFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton

class NoteFragmentUI : AnkoComponent<NoteFragment> {

    lateinit var title : EditText

    override fun createView(ui: AnkoContext<NoteFragment>) = with(ui) {
        relativeLayout {
            padding = dip(30)
            title = editText {
                hintResource = R.string.note_title_hint
                inputType = TYPE_CLASS_TEXT
                maxLines = 1
            }

            floatingActionButton {
                imageResource = android.R.drawable.ic_menu_add
            }.lparams {
                margin = dip(16)
                alignParentBottom()
                alignParentRight()
            }
        }
    }
}
