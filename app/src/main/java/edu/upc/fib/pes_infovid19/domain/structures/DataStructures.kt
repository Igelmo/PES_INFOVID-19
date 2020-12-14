package edu.upc.fib.pes_infovid19.domain.structures

import java.io.Serializable

data class Myth(
    var id: String = "",
    var title: String = "",
    var text: String = "",
    var date: String = "",
    var source: String = ""
) : Serializable

data class TestType(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var procedure: String = "",
    var date: String = "",
    var source: String = ""
) : Serializable

data class RiskPopulation(
    var id: String = "",
    var risk: String = ""
) : Serializable

data class Prevention(
    var id: String = "",
    var title: String = "",
    var text: String = "",
    var image: String = ""
) : Serializable

data class RiskPrevention(
    var id: String = "",
    var date: String = "",
    var image: String = "",
    var recomanacions: Map<String, Prevention> = emptyMap(),
    var source: String = "",
    var title: String = ""
) : Serializable {
    val recomanacionsAsList
        get() = recomanacions.map { (key, value) -> value.copy(id = key) }
}

data class QuestionProbabilityTest(
    var id: String = "",
    var text: String = "",
    var points: Double = 0.0
) : Serializable

data class QuestionVulnerabilityTest(
    var id: String = "",
    var text: String = "",
    var points: Double = 0.0,
    var type: String = ""
) : Serializable