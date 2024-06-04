package br.com.fiap.tests

import model.Sensores
import utils.*
import java.util.Random
import java.util.Scanner

class Main {
    val PH_MIN: Float = 3.0f
    val PH_MAX: Float = 9.0f
    val UMIDADE_MIN: Int = 20
    val UMIDADE_MAX: Int = 50

    val input = Scanner(System.`in`)
    val r = Random()
    val listaSensores: ListaSensoresDesc = ListaSensoresDesc()
    var dia = 0
    val coordenadasSensores = arrayOf(
        "47°55'44\"W,21°00'34\"S",
        "47°55'42\"W,21°00'35\"S",
        "47°55'39\"W,21°00'37\"S",
        "47°55'44\"W,21°00'39\"S",
        "47°55'40\"W,21°00'40\"S",
        "47°55'32\"W,21°00'34\"S",
        "47°55'28\"W,21°00'34\"S",
        "47°55'28\"W,21°00'38\"S",
        "47°55'33\"W,21°00'39\"S"
    )
    val n = coordenadasSensores.size
    var opcao = 0

    init {
        while (opcao < n) {
            val umidade = r.nextInt(UMIDADE_MAX - UMIDADE_MIN) + UMIDADE_MIN
            val ph = PH_MAX - (PH_MAX - PH_MIN) * (r.nextInt(10).toFloat() / 10.0f)
            val potassio = r.nextInt(1000)
            val temp = r.nextInt(40)
            val nSensor = Sensores(opcao, coordenadasSensores[opcao], umidade, ph, potassio, temp)
            listaSensores.insere(nSensor)
            ++opcao
        }
    }

    fun run() {
        var opcao: Int

        do {
            println("DIA $dia")
            println("0 - Sair")
            println("1 - Criar Sensor")
            println("2 - Mostrar Sensores")
            println("3 - Procurar sensor")
            println("4 - Buscar anomalias")
            println("5 - Passar o dia")
            opcao = input.nextInt()
            when (opcao) {
                0 -> {}
                1 -> {
                    val sensor = Sensores()
                    println("Digite a coordenada do sensor: ")
                    input.nextLine()
                    sensor.coordenadas = (input.nextLine())
                    println("Digite o residuo total do solo: ")
                    sensor.umidade = (input.nextInt())
                    println("Digite o pH do solo: ")
                    sensor.setPh(input.nextFloat())
                    println("Digite o oxigenio dissolvido  do solo: ")
                    sensor.potassio = (input.nextInt())
                    println("Digite a temperatura do solo: ")
                    sensor.temperatura = (input.nextInt())
                    listaSensores.insere(sensor)
                    println("Sensor registrado com sucesso!")
                }

                2 -> {
                    listaSensores.apresenta()
                    println("Sensores encontrados: ${listaSensores.length()}")
                }

                3 -> {
                    var cordenadaProcurada: String?
                    println("Digite a coordenada desejada: ")
                    cordenadaProcurada = input.next()
                    println(listaSensores.procurarSensor(cordenadaProcurada) + "\n")
                }

                4 -> {
                    val filaSensoresAnomalia = listaSensores.procurarAnomalia()
                    filaSensoresAnomalia.apresenta()
                    println("Anomalias encontradas: ${filaSensoresAnomalia.length()}")
                }

                5 -> {
                    ++dia
                    listaSensores.passaDia()
                }

                else -> println("Opção Inválida!\n")
            }
        } while (opcao != 0)

        input.close()
    }
}

fun main() {
    val main = Main()
    main.run()
}
