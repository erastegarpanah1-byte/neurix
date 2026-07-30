package com.neurix.feature.chat.presentation

import com.neurix.core.common.MviIntent
import com.neurix.core.common.MviState

data class ChatState(
    val messages: List<ChatMessage> = fakeMessages,
    val isTyping: Boolean = false,
    val inputText: String = ""
) : MviState

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: String = ""
)

sealed interface ChatIntent : MviIntent {
    data class UpdateInput(val text: String) : ChatIntent
    data object SendMessage : ChatIntent
    data object TapMicrophone : ChatIntent
}

val fakeMessages = listOf(
    ChatMessage(
        id = "1",
        text = "Hello! I'm Neurix. How can I help you today?",
        isUser = false,
        timestamp = "10:30 AM"
    ),
    ChatMessage(
        id = "2",
        text = "Can you explain what you can do?",
        isUser = true,
        timestamp = "10:30 AM"
    ),
    ChatMessage(
        id = "3",
        text = "I'm designed to be your intelligent companion. I can assist with research, creative writing, coding, analysis, and much more. Think of me as your always-available AI partner.",
        isUser = false,
        timestamp = "10:31 AM"
    ),
    ChatMessage(
        id = "4",
        text = "That sounds impressive!",
        isUser = true,
        timestamp = "10:31 AM"
    ),
    ChatMessage(
        id = "5",
        text = "Thank you! I'm here whenever you need me. Just ask me anything — I'm ready to help.",
        isUser = false,
        timestamp = "10:32 AM"
    )
)