package com.neurix.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : MviState, Intent : MviIntent, Effect : MviEffect>(
    initialState: State
) {
    private val _state = MutableStateFlow(initialState)
    val state: Flow<State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: Flow<Effect> = _effect.asSharedFlow()

    val currentState: State get() = _state.value

    protected fun setState(reduce: State.() -> State) {
        _state.value = _state.value.reduce()
    }

    protected fun sendEffect(effect: Effect) {
        _effect.tryEmit(effect)
    }

    abstract fun handleIntent(intent: Intent)
}