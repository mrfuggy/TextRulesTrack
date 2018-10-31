package parser.states

import parser.State
import parser.StateMachine

class EmptyState : BaseState(), State {
    override fun handle(fsm: StateMachine) {}
}
