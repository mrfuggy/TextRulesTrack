package parser

import parser.states.EmptyState
import java.util.*

class StateMachine {
    var titleRecords: Map<String, TitleRecord> = mapOf()
    var datesCount: List<DatePair> = listOf()
    var currentDate: Date = Date()
    var totalEps = 0
    private var state: State = EmptyState()

    fun handleString(s: String) {
        state = state.changeState(s)
        state.handle(this)
    }

    fun totalCount(): Int = datesCount.sumBy { it.dayCount }

    fun getTitles() = titleRecords.values

    fun validateDays() {
        if (totalEps != totalCount()) {
            println("actual: $totalEps expected:${totalCount()}")
        }
    }
}
