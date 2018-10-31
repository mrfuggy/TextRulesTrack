package parser.states

import parser.State
import parser.StateMachine

class ErrorState(private val message:String, private val str:String) : BaseState(), State {
    override fun handle(fsm: StateMachine) = println("$message $str")
}
