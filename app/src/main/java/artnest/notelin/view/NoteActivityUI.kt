package artnest.notelin.view

import artnest.notelin.controller.NoteActivity
import artnest.notelin.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class NoteActivityUI : AnkoComponent<NoteActivity> {

    override fun createView(ui: AnkoContext<NoteActivity>) = with(ui) {
        frameLayout {
            id = R.id.fragment_container
        }
    }
}
