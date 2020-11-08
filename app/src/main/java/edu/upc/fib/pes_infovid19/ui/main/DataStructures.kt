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
    var risk: String = ""
)

data class Prevention(
    var image: String = "",
    var text: String = "",
    var title: String = ""
)

data class RiskPrevention(
    var date: String = "",
    var image: String = "",
    var recomanacions: Map<String, Prevention> = emptyMap(),
    var source: String = "",
    var title: String = ""
)