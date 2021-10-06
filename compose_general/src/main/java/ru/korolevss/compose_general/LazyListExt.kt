package ru.korolevss.compose_general

import androidx.compose.foundation.lazy.LazyListState

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0