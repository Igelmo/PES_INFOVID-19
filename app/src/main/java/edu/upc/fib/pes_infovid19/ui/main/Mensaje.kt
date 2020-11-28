package edu.upc.fib.pes_infovid19.ui.main

class Mensaje {
    lateinit var nombre: String
    lateinit var mensaje: String
    lateinit var hora: String

    constructor() {}

    constructor(nombre: String, mensaje: String, hora: String) {
        this.nombre = nombre
        this.mensaje = mensaje
        this.hora = hora
    }

    fun getnombre1(): String {
        return nombre
    }

    fun getmensaje1(): String {
        return mensaje
    }

    fun gethora1(): String {
        return hora
    }

    fun setnombre1(nombre: String) {
        this.nombre = nombre
    }

    fun setmensaje1(mensaje: String) {
        this.mensaje = mensaje
    }

    fun sethora1(hora: String) {
        this.hora = hora
    }
}