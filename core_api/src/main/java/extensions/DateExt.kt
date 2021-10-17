package extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormatString() : String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yy hh:mm::ss", Locale.getDefault())
    val date = Date(this)
    return simpleDateFormat.format(date)
}