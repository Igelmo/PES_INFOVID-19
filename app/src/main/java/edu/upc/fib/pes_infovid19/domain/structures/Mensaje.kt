package edu.upc.fib.pes_infovid19.domain.structures

open class Mensaje {

    lateinit var nombre: String
    lateinit var mensaje: String
    var data: String = ""

    fun Mensaje(nombre: String, mensaje: String, data: String) {
        this.nombre = nombre
        this.mensaje = mensaje
        this.data = data

    }





}