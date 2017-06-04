package artnest.notelin

import org.jetbrains.anko.*

class NoteFragmentUI : AnkoComponent<NoteFragment> {

    override fun createView(ui: AnkoContext<NoteFragment>) = with(ui) {
        verticalLayout {
            var title = editText {
                id = R.id.note_title
                hintResource = R.string.note_title_hint
            }
        }
    }
}