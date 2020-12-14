package edu.upc.fib.pes_infovid19

class User {
    var email = ""
    var username = ""
    var name = ""
    var type = ""
    var password = ""

    fun addInfo(email: String, username: String, name: String, type: String, password: String) {
        this.email = email
        this.username = username
        this.name = name
        this.type = type
        this.password = password
    }


}

