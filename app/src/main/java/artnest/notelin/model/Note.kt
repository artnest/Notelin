package artnest.notelin.model

import java.util.*

data class Note(var mTitle: String = "") {

    val mId = UUID.randomUUID().toString()
}
