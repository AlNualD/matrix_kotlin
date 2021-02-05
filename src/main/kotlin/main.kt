import matrix.*

fun main(args: Array<String>) {

    var arr : Array<Array<Int>> = Array(4) { Array(6) { 0 } }
    val table = Array(3) { Array(3) { 0 } }
    table[0] = arrayOf(1, 2, 3)
    table[1] = arrayOf(4, 5, 6)
    table[2] = arrayOf(7, 8, 9)
    var matr : IntMatrix = IntMatrix(Array(3) { Array(3) { 2 } })
    var matr2 : IntMatrix = IntMatrix(table)
    println(matr)

    println(matr.summarize(matr2))

    println(matr2.multiplication(2))

    println("aaaa")


    var matr3 : IntMatrix = IntMatrix(Array(5) { Array(3) { 2 } })
    println(matr3)
    println("\n\n")
    println(matr2)
    println("\n\n !!!! \n")

    println(matr3.multiplication(matr2))

    println("trranspooose")
    println(matr2.transpose())

    println(matr2.determinant())

    println("\n\n\nCOMPLEX\n")
    var compl : ComplexNum = ComplexNum(4.6,-2.3)
    println("Compl $compl\n")
    var cmatr1 : ComplexMatrix = ComplexMatrix(Array(5) { Array(3) { ComplexNum(1.1, 1.1) } })
    var cmatr3 : ComplexMatrix = ComplexMatrix(Array(3) { Array(3) { ComplexNum(1.1, 1.1) } })
    val ctable = Array(3) { Array(3) { ComplexNum(3.0, 1.2) } }
    ctable[0] = arrayOf(ComplexNum(1.1,1.0), ComplexNum(1.4,3.0),ComplexNum(3.1,-2.0))
    ctable[1] = arrayOf(ComplexNum(2.1,2.0), ComplexNum(2.4,-4.0),ComplexNum(2.1,-4.0))
    ctable[2] = arrayOf(ComplexNum(3.1,-3.0), ComplexNum(3.4,5.0),ComplexNum(1.1,-6.0))
    var cmatr2 : ComplexMatrix = ComplexMatrix(ctable)

    println(cmatr1)
    println(cmatr3.summarize(cmatr2))
    println(cmatr1.multiplication(cmatr2))
    println(cmatr2.transpose())
}