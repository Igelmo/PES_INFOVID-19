package edu.upc.fib.pes_infovid19.ui.main

data class Myth(
    var id: String = "",
    var title: String = "",
    var text: String = "",
    var date: String = "",
    var source: String = ""
)

data class TestType(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var procedure: String = "",
    var date: String = "",
    var source: String = ""
)

data class RiskPopulation(
    var id: String = "",
    var risk: String = "",
    var description: String = ""
)