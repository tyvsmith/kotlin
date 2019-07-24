interface A {
    val x: Int
}

fun foo(a: A) {
    val x = a.x + 1
}
//interface S
//
//interface Comparable<T>
//
//interface A : S, Comparable<A>
//
//interface B : S, Comparable<B>
//
//fun <K> select(x: K, y: K): K = null!!
//
////fun foo(x: A<*>) = when (x) {
////    is B -> x.value
////    is C -> x.value
////    else -> 1
////}
//
////fun test_1() = if (true) { 1 } else { 1.0 }
//
////fun test_2(x: B, y: C) = select(x, y)
//
//fun test_3(a: A, b: B) = select(a, b)
//
////fun test_4(a: A, b: B) = if (true) a else  b