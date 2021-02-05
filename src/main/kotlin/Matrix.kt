package matrix

import kotlin.math.abs

class IntMatrix(val matr: Array<Array<Int>>) {

    private val col : Int = this.matr[0].size
    private val row : Int = this.matr.size

    constructor(col: Int, row: Int) : this(Array(row) { Array(col) { 0 } })

    override fun toString(): String {

        var answ = ""
        for (i in matr) {
            for (j in i) {
                answ = "$answ$j "
            }

            answ += "\n"
        }
        return answ
    }

    fun summarize(_matr: IntMatrix) : IntMatrix? {
        if ((row != _matr.row)||(col != _matr.col)) return null

        val answ : Array<Array<Int>> = matr

        for (i in 0 until col) {
            for (j in 0 until row) {
                answ[i][j] += _matr.matr[i][j]
            }
        }

        return IntMatrix(answ)
    }

    fun multiplication(a: Int) : IntMatrix {
        val answ  = matr
        for (i in 0 until  col) {
            for (j in 0 until  row) {
               answ[i][j] *= a
            }
        }
        return IntMatrix(answ)
    }

    private  fun linemult(ifix: Int, jfix: Int, _matr: IntMatrix) : Int {
        var answ : Int = 0
        for (i in 0 until col) {
            answ += matr[ifix][i] * _matr.matr[i][jfix]
        }
        return answ
    }
    fun multiplication(_matr: IntMatrix) : IntMatrix? {
        if (col != _matr.row) return null
        val answ = Array(row) { Array(_matr.col) { 0 } }
        for (i in 0 until row) {
            for ( j in 0 until _matr.col) {
                answ[i][j] =linemult(i, j, _matr)
            }
        }
        return IntMatrix(answ)

    }

    fun transpose() : IntMatrix {
        val answ = Array(row) { Array(col) { 0 } }
        for (i in 0 until col) {
            for (j in 0 until row) {
                answ[i][j] = matr[j][i]
            }
        }
        return IntMatrix(answ)
    }


    fun determinant() : Int? {
        if (col == 2 && row == 2) {
            return matr[0][0]*matr[1][1] - matr[0][1] * matr[1][0]
        }
        if (col == 3 && row == 3) {
            return (matr[0][0]*matr[1][1]*matr[2][2])  + (matr[2][0] * matr[0][1] * matr[1][2]) + (matr[1][0] * matr[2][1] *matr[0][2]) - (matr[2][0]*matr[1][1]*matr[0][2]) - (matr[1][0]*matr[0][1]*matr[2][2]) - (matr[0][0] * matr[1][2] *matr[2][1])
        }
        return null;

    }


}

class ComplexNum(private var re: Double, private var im: Double) {
    private fun makeDoubleStr(d : Double) : String {
        return String.format("%5.2f",d)
    }
    override fun toString(): String {
        if (im < 0) {
            var imm = abs(im)
            return "${makeDoubleStr(re)}-${makeDoubleStr(imm)}i"
        }
        return "${makeDoubleStr(re)}+${makeDoubleStr(im)}i"
    }

    override fun equals(other: Any?): Boolean {
        if (other == this) return true
        if (other !is ComplexNum) return false
        val that : ComplexNum = other as ComplexNum
        return (im == that.im)&&(re == that.re) //super.equals(other)
    }

    public fun summarize(num: ComplexNum) : ComplexNum {
        return ComplexNum(re + num.re, im + num.im)
    }
    public fun subtract(num: ComplexNum) : ComplexNum {
        return ComplexNum(re - num.re, im - num.im)
    }
    public fun multiplicat(num: ComplexNum) : ComplexNum {
        return  ComplexNum(re * num.re - im * num.im, re * num.im + im * num.re)
    }
    public fun division(num: ComplexNum) : ComplexNum {
        val zn = num.re * num.re + num.im * num.im
        return ComplexNum((re * num.re + im * num.im) / zn, (im * num.re - re * num.im) / zn)
    }
    public fun conjugation () : ComplexNum {
        return ComplexNum(re, -1 * im)
    }
}

class ComplexMatrix(val matr: Array<Array<ComplexNum>>) {
    private val col : Int = this.matr[0].size
    private val row : Int = this.matr.size

    constructor(col: Int, row: Int) : this(Array(row) { Array(col) { ComplexNum(0.0, 0.0) } })


    override fun toString(): String {

        var answ : String = ""
        for (i in matr) {
            for (j in i) {
                answ = "$answ$j "
            }

            answ += "\n"
        }
        return answ
    }

    fun summarize(_matr: ComplexMatrix) : ComplexMatrix? {
        if ((row != _matr.row)||(col != _matr.col)) return null

        val answ : Array<Array<ComplexNum>> = matr

        for (i in 0 until col) {
            for (j in 0 until row) {
                answ[i][j] = matr[i][j].summarize(_matr.matr[i][j])
            }
        }

        return ComplexMatrix(answ)
    }



    private  fun linemult(ifix: Int, jfix: Int, _matr: ComplexMatrix) : ComplexNum {
        var answ : ComplexNum = ComplexNum(0.0,0.0)
        for (i in 0 until col) {

            answ = answ.summarize(matr[ifix][i].multiplicat( _matr.matr[i][jfix]))
        }
        return answ
    }
    fun multiplication(_matr: ComplexMatrix) : ComplexMatrix? {
        if (col != _matr.row) return null
        val answ = Array(row) { Array(_matr.col) { ComplexNum(0.0, 0.0) } }
        for (i in 0 until row) {
            for ( j in 0 until _matr.col) {
                answ[i][j] =linemult(i, j, _matr)
            }
        }
        return ComplexMatrix(answ)

    }

    fun transpose() : ComplexMatrix {
        val answ = Array(row) { Array(col) { ComplexNum(0.0, 0.0) } }
        for (i in 0 until col) {
            for (j in 0 until row) {
                answ[i][j] = matr[j][i]
            }
        }
        return ComplexMatrix(answ)
    }


}