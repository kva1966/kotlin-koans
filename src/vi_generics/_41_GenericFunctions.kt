package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun <T, REC: Collection<T>, C: MutableCollection<T>> REC.partitionTo(out1: C, out2: C, predicate: (T) -> Boolean): Pair<C, C> {
    val (c1, c2) = this.partition(predicate)
    c1.toCollection(out1)
    c2.toCollection(out2)
    
    return Pair(out1, out2)    
}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
//    task41()
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
//    task41()
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}