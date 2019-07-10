package techtalk

import java.math.BigDecimal
import kotlin.math.roundToInt




////////////////////////
//  THE BASICS
///////////////////////


// variables
var anInteger: Int = 300;
var aString: String = "What";

// immutable variables
val immutableInteger: Int = 300;

// string interpolation
val immutableString: String = "What ${anInteger}";

// this is how you declare a function
fun simples(): Int {
    // useful for tests!
    var `cash dollars`: Int = 1_000_000;

    // control flow statements
    var taxed = if (`cash dollars` > 5_000_000) {
        `cash dollars` * 0.15;
    } else {
        `cash dollars` * 0.01;
    }

    // ^ fun fact - there no ternary operator in kotlin

    // when statements (super switches!)
    when (taxed.roundToInt()) {
        in 1..1000 -> print("sadness")
        in 1001..2000 -> print("less sadness")
        else -> print("happy?")
    }

    // inline functions (access to outer scope)
    fun embiggen(t: Int): Int {
        return `cash dollars` * t
    }

    // still need to return
    return embiggen(5)
}

// shorthand for simple functions
// also look default method parameters
fun multiply(a: Int, b: Int = 10) = a * b


// no more casting

fun smartCasts(x: Any) {
    if (x is String) {
        print(x.length)
    }

    if (x is Int) {
        print(x * 5)
    }
}





////////////////////////
//  NULLS BE GONE
///////////////////////

fun feedMe(food: String, amount: Int): Int {
    // wont even compile!!
    // return null
    return 5
}

// wont compile!
// feedMe(null, 5)
// feedMe("foo", null)
fun maybeFeedMe(foo: String?, amount: Int = 5){
}

// i can pass nulls...
// and i have named arguments
var x = maybeFeedMe(null, amount = 10)





////////////////////////
//  TYPES & CLASSES
///////////////////////

class Person(val firstName: String, var lastName: String) {
    val customerKey = firstName.toUpperCase()

    fun eat() {
        print("i ate")
    }
}

// extension methods/properties

fun Person.morph() {
    print("turns into a cat")
}

fun helloTom() {
    val tom = Person("Tom", "Jones")        // <-- No `new` keyword!?

    tom.firstName           // <-- this cant change
    tom.lastName            // <-- i can edit this one
    tom.customerKey

    tom.eat()
    tom.morph()             // <-- try doing that in java! (p.s. no static methods)
}

// enum classes
enum class BeanType {
    Magic,
    NotMagic,
    Paula
}

// you get a ton of stuff here
// equals/hashcode/toString/copy/componentAccessors

data class MagicBean(
    val kind: BeanType,
    val price: BigDecimal)

// ^ you can add your own functions too, dont be scared

fun helloBeans() {
    val originalBean = MagicBean(BeanType.Magic, BigDecimal(30))

    // no more builders
    val premiumBean = originalBean.copy(price = BigDecimal(40))
}

// inline classes (experimental feature!)
inline class Password(val value: String)

fun secrets() {
    // there is no additional heap or boxing cost
    // but you get type safety
    val securePassword = Password("Don't try this in production")
}




////////////////////////
//  CHECKED EXCEPTIONS
///////////////////////


// there are none
// thank u, next





////////////////////////
//  GENERICS
///////////////////////

// !! Still subject to type erasure at compile time - thanks Mr Gosling

// generic functions
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

// generic extension functions
fun <T> T.basicToString(): String {
    return this.toString()
}

// generic classes
class Box<T>(t: T) {
    var value = t
}

fun typeInference() {
    val box1: Box<Int> = Box<Int>(1)
    val box2: Box<Int> = Box(2)
    val box3 = Box(2)        // <-- holy type inference batman!
}

// there's a ton more here the docs
//  - covariance
//  - contravariance
//  - constraints
//  - use-site variance _without wildcards_
//  - reified/inline generics (copying to use site)
// if you're interested read more on this - i am not an expert in java generics



////////////////////////
//  Collections
///////////////////////



fun getFunctional(): List<String> {
    val strings = listOf("foo", "bar", "zomg")
    return strings
            .filter { it.length == 3 }
            .sortedBy { it }
            .map { it.toUpperCase() }
}


// there's a ton here too! If you come from C# or python you'll
// feel right at home



////////////////////////
//  Lambdas & Higher order functions
///////////////////////



fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun letsFold(): String {
    val items = listOf(1, 2, 3, 4, 5)

    val joinedToString = items.fold(
        "Elements:",
        { acc, i -> "$acc $i" }) // <-- lambdas

    return joinedToString
}

fun functionTypes() {
    val person = Person("Tom", "Jones")

    // functions are types
    val doEat = person::eat
    doEat()

    // ^ you can bind to constructors, anything...
}

// function types with receivers

class HTML {
    fun body() {  }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

fun `is this ruby or kotlin?`() {

    html {
        body()
    }

    // ^ i showed you this, please dont abuse it
}




////////////////////////
//  In Summary!
///////////////////////

/*

What's Java Got?

 - checked exceptions
 - primitive types (this always confused me)
 - static members
 - non private fields
 - wildcard-types
 - ternary operator

What's Kotlin Got?

 - Null Safety - Null is part of the type system
 - Lambda Expressions
 - Extension functions/properties
 - Smart Casts
 - String Interpolation/Templates
 - Properties
 - Primary Constructors
 - First class delegation (makes composition easier)
 - Type inference that works
 - Singletons (this is questionable)
 - Range expressions
 - Operator overloading
 - Companion objects
 - Data Classes
 - Coroutines (all supported by libraries)
 - Separate mutable/immutable collection interfaces
 - and more!!


tl;dr try kotlin today


 */






fun main(args: Array<String>) {
}
