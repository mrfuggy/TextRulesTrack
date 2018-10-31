package parser.states

import parser.State
import parser.StateMachine
import parser.TitleRecord

class TitleState(private val matches: MatchGroupCollection) : BaseState(), State {
    override fun handle(fsm: StateMachine) {
        val title = matches["title"]?.value ?: ""
        val eps = matches["eps"]?.value?.toInt() ?: 0
        val addTime = matches["addTime"]?.value?.toInt() ?: 0
        val score = matches["score"]?.value?.toInt() ?: 0
        val tags = matches["tags"]?.value?.split(", ") ?: listOf()
        val drop = matches["drop"]?.value == "drop"
        val cont = matches["cont"]?.value?.startsWith("+") ?: false

        val titleRecord = fsm.titleRecords[title]
        if (titleRecord == null) {
            val record = TitleRecord(title, fsm.currentDate, fsm.currentDate, eps, score, addTime, tags, drop, cont)
            fsm.titleRecords = fsm.titleRecords + Pair(title, record)
            fsm.datesCount.last().dayCount += record.getTotal()
        }
        else {
            val record = titleRecord.change(fsm.currentDate, eps, score, addTime, tags, drop, cont)
            fsm.titleRecords = fsm.titleRecords + Pair(title, record)
            fsm.datesCount.last().dayCount += record.getTotal(titleRecord)
        }
    }
}
