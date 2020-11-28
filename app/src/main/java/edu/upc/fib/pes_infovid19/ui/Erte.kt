package edu.upc.fib.pes_infovid19.ui

class Erte {
    var email = "email"
    var nom = "nom"
    var cognoms = "cognoms"
    var dni = "dni"
    var empresa = "empresa"
    var compte_bancari = "compte_bancari"
    var num_telefon = "num_telefon"
    var codiPostal = "-1"
    var base_reguladora = "base_reguladora"

    /*fun addEmail(email: String){
        this.email = email
        print("email")
    }*/

    fun addInfo(email: String, nom: String, cognoms: String, dni: String, empresa: String, compte_bancari: String, num_telefon: String, codiPostal: String, base_reguladora: String) {
        this.email = email
        this.nom = nom
        this.cognoms = cognoms
        this.dni = dni
        this.empresa = empresa
        this.compte_bancari = compte_bancari
        this.num_telefon = num_telefon
        this.codiPostal = codiPostal
        this.base_reguladora = base_reguladora
    }
}