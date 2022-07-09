// #1. Create Person without Builder ----------------------------------------------------------------------
class Person1(
    val name: String,
    val age: Int,
    val salary: Double,
    // ... we do not know how many fields ...
    // Every field needs to be added to constructor, and create many overloaded constructors for every combination
    // of fields set
)


// #2. Create Person with Builder ----------------------------------------------------------------------
class Person2(
    var name: String? = null,
    var age: Int? = null,
    var salary: Double? = null,
    // ... we do not know how many fields ...
)


interface PersonBuilder {
    fun setName(name: String): PersonBuilder
    fun setAge(age: Int): PersonBuilder
    fun setSalary(salary: Double): PersonBuilder

    fun build(): Person2
}

class PersonBuilderImpl : PersonBuilder {
    var person2: Person2 = Person2()

    override fun setName(name: String): PersonBuilder {
        person2.name = name
        return this
    }

    override fun setAge(age: Int): PersonBuilder {
        person2.age = age
        return this
    }

    override fun setSalary(salary: Double): PersonBuilder {
        person2.salary = salary
        return this
    }

    // important! create Person
    override fun build(): Person2 {
        return person2
    }
}


fun main() {

    // Person 2 Option -A-          the best way :)
    val person1: Person1 = Person1("Robert", 25, 2000.0)

    // Person 2 Option -A-
    val personBuilder: PersonBuilder = PersonBuilderImpl()
    personBuilder.setName("Cris")
    personBuilder.setAge(35)
    personBuilder.setSalary(1000.0)
    val person2A = personBuilder.build()

    // Person 2 Option -B-
    val person2B: Person2 = PersonBuilderImpl()
        .setName("Bill")            // add field to constructor
        .setAge(45)                 // add field to constructor
        // - no one field -
        .build()                    // create person!!!

    println("${person1.name}, ${person1.age}, ${person1.salary}")
    println("${person2A.name}, ${person2A.age}, ${person2A.salary}")
    println("${person2B.name}, ${person2B.age}, ${person2B.salary}")
}


