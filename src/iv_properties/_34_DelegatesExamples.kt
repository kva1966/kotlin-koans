package iv_properties

import util.TODO
import util.doc34
import kotlin.reflect.KProperty

class LazyPropertyUsingDelegates(val initializer: () -> Int) {
    val lazyValue: Int by LazyDelegate(initializer)
    // Using built-in.
    //val lazyValue: Int by lazy(initializer)
}

fun todoTask34(): Lazy<Int> = TODO(
    """
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """,
    documentation = doc34()
)

class LazyDelegate(val initialiser: () -> Int) {
    private var isInitialised: Boolean = false
    private var value: Int = 0
    
    operator fun getValue(thisRef: LazyPropertyUsingDelegates, property: KProperty<*>): Int {
        if (!isInitialised) {
            isInitialised = true
            value = initialiser()
        }
        
        return value
    } 
}