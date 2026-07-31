package com.neurix.feature.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.neurix.core.design.NeurixColors
import com.neurix.core.design.NeurixDimens
import com.neurix.core.navigation.Screen
import com.neurix.core.ui.composables.PlaceholderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToDetail: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeurixColors.Background)
    ) {
        Spacer(modifier = Modifier.height(NeurixDimens.PaddingLarge))
        
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            color = NeurixColors.OnSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = NeurixDimens.PaddingMedium)
        )
        
        Spacer(modifier = Modifier.height(NeurixDimens.PaddingMedium))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = NeurixDimens.PaddingMedium,
                    vertical = NeurixDimens.PaddingSmall
                ),
            verticalArrangement = Arrangement.spacedBy(NeurixDimens.PaddingLarge)
        ) {
            // Profile Card
            ProfileCard()

            // Preferences Section
            SettingsSection(title = "Preferences") {
                SettingsRow(
                    icon = Icons.Filled.DarkMode,
                    title = "Theme",
                    subtitle = "Dark",
                    iconTint = NeurixColors.Accent,
                    onClick = { onNavigateToDetail(Screen.Theme) }
                )
                SettingsRow(
                    icon = Icons.Filled.Language,
                    title = "Language",
                    subtitle = "English",
                    iconTint = NeurixColors.Primary,
                    onClick = { onNavigateToDetail(Screen.Language) }
                )
            }

            // Features Section
            SettingsSection(title = "Features") {
                SettingsRow(
                    icon = Icons.Filled.Mic,
                    title = "Voice",
                    subtitle = "Configure speech and audio",
                    iconTint = NeurixColors.Secondary,
                    onClick = { onNavigateToDetail(Screen.Voice) }
                )
                SettingsRow(
                    icon = Icons.Filled.Memory,
                    title = "Memory",
                    subtitle = "Manage what Neurix remembers",
                    iconTint = Color(0xFFF59E0B),
                    onClick = { onNavigateToDetail(Screen.Memory) }
                )
                SettingsRow(
                    icon = Icons.Filled.Security,
                    title = "Permissions",
                    subtitle = "Control device access",
                    iconTint = Color(0xFF22C55E),
                    onClick = { onNavigateToDetail(Screen.Permissions) }
                )
            }

            // About Section
            SettingsSection(title = "About") {
                SettingsRow(
                    icon = Icons.Filled.Info,
                    title = "About Neurix",
                    subtitle = "Version 1.0.0",
                    iconTint = NeurixColors.OnSurfaceMuted,
                    onClick = { onNavigateToDetail(Screen.About) }
                )
            }

            Spacer(modifier = Modifier.height(NeurixDimens.PaddingLarge))
        }
    }
}

@Composable
fun ProfileCard() {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(NeurixDimens.CornerLarge),
        colors = CardDefaults.outlinedCardColors(
            containerColor = NeurixColors.Surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(NeurixDimens.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(NeurixDimens.AvatarSizeLarge)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(NeurixColors.Primary, NeurixColors.Secondary)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "N",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(NeurixDimens.PaddingMedium))

            Column {
                Text(
                    text = "Neurix User",
                    style = MaterialTheme.typography.titleMedium,
                    color = NeurixColors.OnSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "neurix.user@example.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = NeurixColors.OnSurfaceMuted
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = NeurixColors.OnSurfaceMuted,
            modifier = Modifier.padding(bottom = NeurixDimens.PaddingSmall)
        )

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(NeurixDimens.CornerLarge),
            colors = CardDefaults.outlinedCardColors(
                containerColor = NeurixColors.Surface
            ),
            border = CardDefaults.outlinedCardBorder().copy(width = 0.dp)
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconTint: Color,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = NeurixDimens.PaddingMedium,
                    vertical = NeurixDimens.PaddingMedium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(NeurixDimens.CornerSmall))
                    .background(iconTint.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(NeurixDimens.PaddingMedium))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeurixColors.OnSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = NeurixColors.OnSurfaceMuted
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = NeurixColors.OnSurfaceMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SettingsDetailScreen(
    featureName: String,
    description: String,
    onBack: () -> Unit
) {
    PlaceholderScreen(
        featureName = featureName,
        description = description,
        onBack = onBack
    )
}
