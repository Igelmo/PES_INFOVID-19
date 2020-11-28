package edu.upc.fib.pes_infovid19.ui.main

open class Mensaje(var nombre: String, var mensaje: String, var data: String) {

    fun getnombre1(): String {
        return nombre
    }

    fun getmensaje1(): String {
        return mensaje
    }

    fun getdata1(): String {
        return data
    }

    fun setnombre1(nombre: String) {
        this.nombre = nombre
    }

    fun setmensaje1(mensaje: String) {
        this.mensaje = mensaje
    }

    fun setdata1(data: String) {
        this.data = data
    }
}