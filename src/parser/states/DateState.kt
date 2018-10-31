package parser.states

import parser.DatePair
import parser.State
import parser.StateMachine
import java.util.*

class DateState(private val matches: MatchGroupCollection) : BaseState(), State {
    private val calendar = GregorianCalendar()

    override fun handle(fsm: StateMachine) {
        calendar.set(matches["year"]?.value?.toInt() ?: 0,
                (matches["month"]?.value?.toInt() ?: 0) - 1,
                matches["day"]?.value?.toInt() ?: 0)
        val date = calendar.time
        fsm.validateDays()
        fsm.datesCount += DatePair(date, 0)
        fsm.currentDate = date
        fsm.totalEps = matches["total"]?.value?.toInt() ?: fsm.totalEps
    }
}
