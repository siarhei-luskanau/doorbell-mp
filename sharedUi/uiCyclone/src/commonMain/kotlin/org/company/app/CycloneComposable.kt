package org.company.app

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.company.app.theme.AppTheme
import org.company.app.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.IndieFlower_Regular
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.Res
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.cyclone
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.ic_cyclone
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.ic_dark_mode
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.ic_light_mode
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.ic_rotate_right
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.open_github
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.run
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.stop
import siarhei.luskanau.doorbell.mp.ui.cyclones.resources.theme

@Composable
fun CycloneComposable() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.cyclone),
                fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
                style = MaterialTheme.typography.displayLarge
            )

            var isAnimate by remember { mutableStateOf(false) }
            val transition = rememberInfiniteTransition()
            val rotate by transition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing)
                )
            )

            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
                    .run { if (isAnimate) rotate(rotate) else this },
                imageVector = vectorResource(Res.drawable.ic_cyclone),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = null
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { isAnimate = !isAnimate },
                content = {
                    Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        stringResource(if (isAnimate) Res.string.stop else Res.string.run)
                    )
                }
            )

            var isDark by LocalThemeIsDark.current
            val icon = remember(isDark) {
                if (isDark) {
                    Res.drawable.ic_light_mode
                } else {
                    Res.drawable.ic_dark_mode
                }
            }

            ElevatedButton(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ).widthIn(min = 200.dp),
                onClick = { isDark = !isDark },
                content = {
                    Icon(vectorResource(icon), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(Res.string.theme))
                }
            )

            TextButton(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ).widthIn(min = 200.dp),
                onClick = { }
            ) {
                Text(stringResource(Res.string.open_github))
            }
        }
    }
}
