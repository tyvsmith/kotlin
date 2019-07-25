fun <T> materialize(): T = null!!

fun <K> select(x: K, y: K): K

fun test() = select(1, "")