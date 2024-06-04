package utils

import model.Sensores

class FilaSensoresAnomalia {
    private var ini: NO? = null
    private var fim: NO? = null

    fun init() {
        this.fim = null
        this.ini = this.fim
    }

    val isEmpty: Boolean
        get() = this.ini == null && this.fim == null

    fun enqueue(elem: Sensores?) {
        val novo = NO()
        novo.dado = elem
        if (this.isEmpty) {
            this.ini = novo
        } else {
            fim!!.prox = novo
        }

        this.fim = novo
    }

    fun dequeue(): Sensores? {
        val n = ini?.dado
        this.ini = ini?.prox
        if (this.ini == null) {
            this.fim = null
        }

        return n
    }

    fun first(): Sensores? {
        return ini?.dado
    }

    fun apresenta() {
        var aux = this.ini
        println("\n *** LISTA ***")

        while (aux != null) {
            println("\t " + aux.dado.toString())
            aux = aux.prox
        }
    }

    fun esvaziar() {
        while (this.ini != null) {
            this.ini = ini?.prox
        }
    }

    fun length(): Int {
        var cont = 0

        var aux = this.ini
        while (aux != null) {
            ++cont
            aux = aux.prox
        }

        return cont
    }

    inner class NO {
        var dado: Sensores? = null
        var prox: NO? = null
    }
}
