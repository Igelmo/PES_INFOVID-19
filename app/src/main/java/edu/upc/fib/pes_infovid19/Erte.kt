package edu.upc.fib.pes_infovid19

class Erte {
    var email = "email"
    var nom = "nom"
    var cognoms = "cognoms"
    var dni = "dni"
    var empresa = "empresa"
    var localitat = "localitat"
    var provincia = "provincia"
    var codiPostal = "-1"

    /*fun addEmail(email: String){
        this.email = email
        print("email")
    }*/

    fun addInfo(email: String, nom: String, cognoms: String, dni: String, empresa: String, localitat: String, provincia: String, codiPostal: String) {
        this.email = email
        this.nom = nom
        this.cognoms = cognoms
        this.dni = dni
        this.empresa = empresa
        this.localitat = localitat
        this.provincia = provincia
        this.codiPostal = codiPostal
    }
}