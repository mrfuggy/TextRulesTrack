package parser.states

import parser.State
import parser.rules

abstract class BaseState {
    private fun matches(s: String, patternName: String): MatchGroupCollection? {
        val parsed = rules.getValue(patternName).matches(s)
        return if (parsed) rules.getValue(patternName).matchEntire(s)?.groups
        else null
    }

    fun changeState(s: String): State {
        val emptyMatches = matches(s, "EmptyLine")
        if (emptyMatches != null) {
            return EmptyState()
        }

        val dateMatches = matches(s, "Date")
        if (dateMatches != null) {
            return DateState(dateMatches)
        }

        val titleRecordMatches = matches(s, "TitleRecord")
        if (titleRecordMatches != null) {
            return TitleState(titleRecordMatches)
        }

        return ErrorState("unknown", s)
    }
}
