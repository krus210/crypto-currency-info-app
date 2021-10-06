package extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

inline fun <T> MutableStateFlow<T>.safeUpdate(transform: (T) -> T) {
    compareAndSet(value, transform(value))
}

fun <T> MutableSharedFlow<T>.emitWithScope(scope: CoroutineScope, value: T) {
    scope.launch { emit(value) }
}