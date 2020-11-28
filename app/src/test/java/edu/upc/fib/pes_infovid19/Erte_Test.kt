package edu.upc.fib.pes_infovid19

import edu.upc.fib.pes_infovid19.ui.Erte
import org.junit.Assert.assertEquals
import org.junit.Test

class Erte_Test {


    @Test
    fun addicionTest() {
        var newErte = Erte()
        newErte.addInfo("carlesllongueras@hotmail.com", "carles", "llongueras aparicio", "1234P", "Upc", "bcn", "cat", "0065")
        assertEquals("carlesllongueras@hotmail.com", newErte.email)
        assertEquals("carles", newErte.nom)
        assertEquals("llongueras aparicio", newErte.cognoms)
        assertEquals("1234P", newErte.dni)
        assertEquals("Upc", newErte.empresa)
        assertEquals("bcn", newErte.localitat)
        assertEquals("cat", newErte.provincia)
        assertEquals("0065", newErte.codiPostal)
    }


}