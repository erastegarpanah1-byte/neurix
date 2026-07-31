package com.neurix.feature.home.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neurix.core.design.NeurixColors
import com.neurix.core.design.NeurixDimens
import com.neurix.core.ui.composables.FadeInView

@Composable
fun HomeScreen(
    onNavigateToChat: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeEffect.NavigateToChat -> onNavigateToChat()
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val glowScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowScale"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeurixColors.Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    Modifier.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                NeurixColors.Background,
                                NeurixColors.Background,
                                NeurixColors.Surface
                            )
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FadeInView {
                Text(
                    text = state.greeting,
                    style = MaterialTheme.typography.displayMedium,
                    color = NeurixColors.OnBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(NeurixDimens.PaddingSmall))

            FadeInView {
                Text(
                    text = "What can I do for you today?",
                    style = MaterialTheme.typography.titleMedium,
                    color = NeurixColors.OnSurfaceMuted
                )
            }

            Spacer(modifier = Modifier.height(NeurixDimens.PaddingXXLarge))

            // Glow ring behind mic button
            Box(
                modifier = Modifier
                    .size(NeurixDimens.GlowRingSize),
                contentAlignment = Alignment.Center
            ) {
                // Animated glow ring
                Box(
                    modifier = Modifier
                        .size(NeurixDimens.GlowRingSize)
                        .scale(glowScale)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    NeurixColors.Primary.copy(alpha = glowAlpha),
                                    NeurixColors.Primary.copy(alpha = 0.1f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Second glow ring
                Box(
                    modifier = Modifier
                        .size(NeurixDimens.GlowRingSize * 0.7f)
                        .scale(glowScale * 1.1f)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    NeurixColors.Accent.copy(alpha = glowAlpha * 0.7f),
                                    NeurixColors.Accent.copy(alpha = 0.05f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Mic button
                IconButton(
                    onClick = { viewModel.handleIntent(HomeIntent.TapMicrophone) },
                    modifier = Modifier
                        .size(NeurixDimens.MicButtonSize)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    NeurixColors.Primary,
                                    NeurixColors.Secondary
                                )
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "Tap to speak",
                        tint = Color.White,
                        modifier = Modifier.size(NeurixDimens.IconSizeLarge)
                    )
                }
            }
        }
    }
}
