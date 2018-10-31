package parser

interface State {
    fun handle(fsm:StateMachine)
    fun changeState(s:String): State
}