package model

class Sensores {
    var idSensor: Int = 0
    var coordenadas: String? = null
    var umidade: Int = 0
    private var ph = 0f
    var potassio: Int = 0
    var temperatura: Int = 0

    constructor()

    constructor(
        numSensor: Int,
        coordenadas: String?,
        umidadeSolo: Int,
        ph: Float,
        potassioSolo: Int,
        temperaturaSolo: Int
    ) {
        this.idSensor = numSensor
        this.coordenadas = coordenadas
        this.umidade = umidadeSolo
        this.ph = ph
        this.potassio = potassioSolo
        this.temperatura = temperaturaSolo
    }

    fun getPh(): Double {
        return ph.toDouble()
    }

    fun setPh(ph: Float) {
        this.ph = ph
    }




    override fun toString(): String {
        return """
            
            ID Sensor: ${idSensor}
            Cordenadas: ${coordenadas}
            Residuo total: ${umidade}
            PH: ${ph}
            Oxigênio dissolvido: ${potassio}
            Temperatura da agua: ${temperatura}°
            """.trimIndent()
    }
}