package artnest.notelin.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import artnest.notelin.model.Note
import artnest.notelin.view.NoteFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.support.v4.ctx

class NoteFragment : Fragment() {

    lateinit var noteFragmentUI : NoteFragmentUI

    var mNote = Note()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteFragmentUI = NoteFragmentUI()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        val v = noteFragmentUI.createView(AnkoContext.create(ctx, this))
        noteFragmentUI.title.textChangedListener {
            onTextChanged {
                text, start, before, count -> mNote.mTitle = text.toString()
            }
        }
        return v
    }
}
