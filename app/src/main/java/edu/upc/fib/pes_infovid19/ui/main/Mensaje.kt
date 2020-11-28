package edu.upc.fib.pes_infovid19.ui.main

class Mensaje {
    lateinit var nombre: String
    lateinit var mensaje: String
    lateinit var hora: String

    constructor() {

    }

    constructor(nombre: String, mensaje: String, hora: String) {
        this.nombre = nombre
        this.mensaje = mensaje
        this.hora = hora
    }

    fun getnombre(): String {
        return nombre
    }

    fun getmensaje(): String {
        return mensaje
    }

    fun gethora(): String {
        return hora
    }

    fun setnombre(nombre: String) {
        this.nombre = nombre
    }

    fun setmensaje(mensaje: String) {
        this.mensaje = mensaje
    }

    fun sethora(hora: String) {
        this.hora = hora
    }
}