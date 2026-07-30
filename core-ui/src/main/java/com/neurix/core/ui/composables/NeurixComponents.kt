package com.neurix.core.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.neurix.core.design.NeurixColors
import com.neurix.core.design.NeurixDimens

@Composable
fun NeurixGradientCircle(
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = NeurixDimens.AvatarSize,
    colors: List<androidx.compose.ui.graphics.Color> = listOf(
        NeurixColors.Primary,
        NeurixColors.Secondary
    )
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(colors)
            )
    )
}

@Composable
fun NeurixCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(NeurixDimens.CornerLarge),
        colors = CardDefaults.cardColors(
            containerColor = NeurixColors.Surface
        ),
        content = content
    )
}

@Composable
fun PlaceholderScreen(
    featureName: String,
    description: String,
    onBack: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeurixColors.Background)
    ) {

        if (onBack != null) {
            NeurixTopBar(
                title = featureName,
                onBack = onBack
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = NeurixDimens.PaddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Filled.Construction,
                contentDescription = null,
                tint = NeurixColors.OnSurfaceMuted,
                modifier = Modifier.size(64.dp)
            )

            Spacer(
                modifier = Modifier.height(NeurixDimens.PaddingLarge)
            )

            Text(
                text = featureName,
                style = MaterialTheme.typography.headlineSmall,
                color = NeurixColors.OnSurface,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(NeurixDimens.PaddingSmall)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = NeurixColors.OnSurfaceMuted,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(NeurixDimens.PaddingXLarge)
            )

            Text(
                text = "Coming Soon",
                style = MaterialTheme.typography.labelLarge,
                color = NeurixColors.Primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeurixTopBar(
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = NeurixColors.OnSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = NeurixColors.OnSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = NeurixColors.Background
        )
    )
}

@Composable
fun FadeInView(
    visible: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(300)),
        modifier = modifier
    ) {
        content()
    }
}
