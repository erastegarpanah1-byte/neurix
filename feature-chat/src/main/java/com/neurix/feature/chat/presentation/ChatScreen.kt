package com.neurix.feature.chat.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.automirrored.filled.Sendimport androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neurix.core.design.NeurixColors
import com.neurix.core.design.NeurixDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onNavigateBack: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeurixColors.Background)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(NeurixColors.Primary, NeurixColors.Secondary)
                                )
                            )
                    )
                    Spacer(modifier = Modifier.width(NeurixDimens.PaddingSmall))
                    Text(
                        text = "Neurix",
                        style = MaterialTheme.typography.titleMedium,
                        color = NeurixColors.OnSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = NeurixColors.OnSurface
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = NeurixColors.Surface
            )
        )

        // Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(
                horizontal = NeurixDimens.PaddingMedium,
                vertical = NeurixDimens.PaddingSmall
            ),
            verticalArrangement = Arrangement.spacedBy(NeurixDimens.PaddingSmall)
        ) {
            items(state.messages, key = { it.id }) { message ->
                MessageBubble(message = message)
            }

            // Typing indicator
            if (state.isTyping) {
                item {
                    TypingIndicator()
                }
            }
        }

        // Input Bar
        ChatInputBar(
            text = state.inputText,
            onTextChange = { viewModel.handleIntent(ChatIntent.UpdateInput(it)) },
            onSend = { viewModel.handleIntent(ChatIntent.SendMessage) },
            onMicTap = { viewModel.handleIntent(ChatIntent.TapMicrophone) }
        )
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isUser) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(NeurixColors.Primary, NeurixColors.Secondary)
                        )
                    )
            )
            Spacer(modifier = Modifier.width(NeurixDimens.PaddingSmall))
        }

        Column(
            modifier = Modifier.widthIn(max = 300.dp),
            horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = if (message.isUser) 20.dp else 4.dp,
                            topEnd = if (message.isUser) 4.dp else 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .background(
                        if (message.isUser) {
                            Brush.linearGradient(
                                listOf(
                                    NeurixColors.Primary,
                                    NeurixColors.Secondary
                                )
                            )
                        } else {
                            Brush.linearGradient(
                                listOf(
                                    NeurixColors.SurfaceHigh,
                                    NeurixColors.SurfaceHigh
                                )
                            )
                        }
                    )
                    .padding(
                        horizontal = NeurixDimens.PaddingMedium,
                        vertical = NeurixDimens.PaddingSmall
                    )
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (message.isUser) Color.White else NeurixColors.OnSurface
                )
            }

            if (message.timestamp.isNotEmpty()) {
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = NeurixColors.OnSurfaceMuted,
                    modifier = Modifier.padding(
                        top = 2.dp,
                        start = NeurixDimens.PaddingSmall,
                        end = NeurixDimens.PaddingSmall
                    )
                )
            }
        }

        if (message.isUser) {
            Spacer(modifier = Modifier.width(NeurixDimens.PaddingSmall))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(NeurixColors.SurfaceHigh)
            )
        }
    }
}

@Composable
fun TypingIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "typing")
    val dot1 by infiniteTransition.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(400), RepeatMode.Reverse),
        label = "dot1"
    )
    val dot2 by infiniteTransition.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(400, 150), RepeatMode.Reverse),
        label = "dot2"
    )
    val dot3 by infiniteTransition.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(400, 300), RepeatMode.Reverse),
        label = "dot3"
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(NeurixColors.SurfaceHigh)
            .padding(horizontal = NeurixDimens.PaddingMedium, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .alpha(dot1)
                .clip(CircleShape)
                .background(NeurixColors.OnSurfaceMuted)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .size(8.dp)
                .alpha(dot2)
                .clip(CircleShape)
                .background(NeurixColors.OnSurfaceMuted)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .size(8.dp)
                .alpha(dot3)
                .clip(CircleShape)
                .background(NeurixColors.OnSurfaceMuted)
        )
    }
}

@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit,
    onMicTap: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeurixColors.Surface)
            .padding(
                horizontal = NeurixDimens.PaddingMedium,
                vertical = NeurixDimens.PaddingSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(
                    text = "Message Neurix...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeurixColors.OnSurfaceMuted
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeurixColors.Outline,
                unfocusedBorderColor = NeurixColors.Outline,
                focusedContainerColor = NeurixColors.SurfaceHigh,
                unfocusedContainerColor = NeurixColors.SurfaceHigh,
                cursorColor = NeurixColors.Primary,
                focusedTextColor = NeurixColors.OnSurface,
                unfocusedTextColor = NeurixColors.OnSurface
            ),
            shape = RoundedCornerShape(NeurixDimens.CornerLarge),
            maxLines = 4
        )

        Spacer(modifier = Modifier.width(NeurixDimens.PaddingSmall))

        IconButton(
            onClick = onSend,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(NeurixColors.Primary)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
