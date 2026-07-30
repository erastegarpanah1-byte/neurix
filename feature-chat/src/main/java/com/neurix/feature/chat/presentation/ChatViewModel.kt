package com.neurix.feature.chat.presentation

import com.neurix.core.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : BaseViewModel<ChatState, ChatIntent, Nothing>(
    ChatState()
) {
    override fun handleIntent(intent: ChatIntent) {
        when (intent) {
            is ChatIntent.UpdateInput -> {
                setState { copy(inputText = intent.text) }
            }

            ChatIntent.SendMessage -> {
                // Mock: do nothing
            }

            ChatIntent.TapMicrophone -> {
                // Mock: do nothing
            }
        }
    }
}