package utils

import model.Sensores
import java.util.*

class ListaSensoresDesc {
    var r: Random = Random()
    private val MAXTEMP = 35
    private val MINTEMP = 8
    private val MAXUMIDADE = 80
    private val MINUMIDADE = 50
    private val MAXPH = 7.5f
    private val MINPH = 5.5f
    private var lista: NO? = null

    fun insere(elem: Sensores?) {
        val novo = NO()
        novo.dado = elem
        if (this.lista == null) {
            novo.prox = null
            this.lista = novo
        } else if (novo.dado!!.temperatura > lista!!.dado!!.temperatura) {
            novo.prox = this.lista
            this.lista = novo
        } else {
            var aux = this.lista
            var achou = false

            while (aux!!.prox != null && !achou) {
                if (aux.prox!!.dado!!.temperatura > novo.dado!!.temperatura) {
                    aux = aux.prox
                } else {
                    achou = true
                }
            }

            novo.prox = aux.prox
            aux.prox = novo
        }
    }

    fun remove(cordenada: String): Boolean {
        var achou = false
        if (this.lista != null) {
            if (cordenada.equals(lista!!.dado!!.coordenadas)) {
                achou = true
                this.lista = lista!!.prox
            } else {
                var aux = this.lista

                while (aux!!.prox != null && !achou) {
                    if (!(aux.prox!!.dado!!.coordenadas.equals(cordenada))) {
                        aux = aux.prox
                    } else {
                        achou = true
                        aux.prox = aux.prox!!.prox
                    }
                }
            }
        }

        return achou
    }

    fun length(): Int {
        var cont = 0

        var aux = this.lista
        while (aux != null) {
            ++cont
            aux = aux.prox
        }

        return cont
    }

    fun procurarSensor(cordenada: String): String? {
        var achou = false
        if (this.lista != null) {
            if (cordenada.equals(lista!!.dado!!.coordenadas, ignoreCase = true)) {
                achou = true
                return lista!!.dado.toString()
            }

            var aux = this.lista
            while (aux!!.prox != null && !achou) {
                if (aux.prox!!.dado!!.coordenadas.equals(cordenada, ignoreCase = true)) {
                    achou = true
                    return aux.prox!!.dado.toString()
                }
                aux = aux.prox
            }
        }

        if (!achou) {
            println("Sensor não cadastrado")
        }

        return null
    }

    fun procurarAnomalia(): FilaSensoresAnomalia {
        val listaDeAnomalia: FilaSensoresAnomalia = FilaSensoresAnomalia()
        if (this.lista != null) {
            var aux = this.lista
            while (aux != null) {
                val temp: Int = aux.dado!!.temperatura
                val ph: Double = aux.dado!!.getPh()
                val umidade: Int = aux.dado!!.umidade
                var anomalias = 0
                if (temp > 35 || temp < 8) {
                    ++anomalias
                }

                if (ph > 7.5 || ph < 5.5) {
                    ++anomalias
                }

                if (umidade > 80 || umidade < 50) {
                    ++anomalias
                }

                if (anomalias >= 2) {
                    listaDeAnomalia.enqueue(aux.dado)
                }
                aux = aux.prox
            }
        }

        return listaDeAnomalia
    }

    fun apresenta() {
        var aux = this.lista
        println("\n *** LISTA ***")

        while (aux != null) {
            System.out.println("\t " + aux.dado.toString())
            aux = aux.prox
        }
    }

    fun passaDia() {
        var aux = this.lista
        while (aux != null) {
            val ph = (5.5 + r.nextDouble() * 2.0 + 1.5).toFloat()
            val umidade = 5 + r.nextInt(80) + 50
            val temp = r.nextInt(40)
            val potassio = r.nextInt(1000)
            aux.dado!!.setPh(ph)
            aux.dado!!.umidade = umidade
            aux.dado!!.temperatura = temp
            aux.dado!!.potassio = potassio
            aux = aux.prox
        }
    }

    private inner class NO {
        var dado: Sensores? = null
        var prox: NO? = null
    }
}