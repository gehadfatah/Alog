package com.example.mvvmimlelm

val aVar by lazy { println("I am computing this value")
    "Hola" }
fun main(args: Array<String>) { println(aVar)
  //  println(aVar)
  println(performAlsoOperation())
    val students = arrayOf("Abel", "Bill", "Cindy", "Darla")
    printStudents(*students)
}
data class Person (var name: String = "Abcd") private fun performAlsoOperation() {
    val name = Person().also { currentPerson -> print("Current name is: ${currentPerson.name}\n")
        currentPerson.name = "modifiedName" }.run { "Modified name is: $name\n" }
    print(name) }
fun printStudents(vararg students: String) { for(student in students) println(student) }


/*sealed class Status(){ object Error : Status() class Success : Status() } fun main(){ var successStatus = Status.Success()
    var errorStatus = Status.Error
}*/
/*
data class Point(val x: Int, val y: Int) operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)
operator fun Point.hash(other: Point) = Point(x - other.x, y - other.y) fun main() { val point1 = Point(10, 20) val point2 = Point(20, 30)
    println(point1 + point2)
    println(point1 # point2) }*/
/*
object StringHelper { fun method(param: String): Boolean { return when(param) { "my string" -> true else -> false } } }
class InfoHelperTest {
    @Test fun `test my string`() { assertTrue(StringHelper.method("my string")) }
    @Test fun `test not my string`() {
    assertTrue(StringHelper.method("not my string")) } }*/
