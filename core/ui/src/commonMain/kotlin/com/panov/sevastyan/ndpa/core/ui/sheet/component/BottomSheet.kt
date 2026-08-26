package com.panov.sevastyan.ndpa.core.ui.sheet.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.icon.Close
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.strings.generated.resources.Res
import com.panov.sevastyan.ndpa.core.strings.generated.resources.sheet_close
import com.panov.sevastyan.ndpa.core.ui.button.component.PrimaryButton
import com.panov.sevastyan.ndpa.core.ui.interaction.PressedIndication
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shapes = BottomSheetDefaults.shapes
    val colors = BottomSheetDefaults.colors

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = colors.container,
        shape = shapes.container,
        scrimColor = colors.scrim,
        dragHandle = null,
    ) {
        BottomSheetLayout(
            title = title,
            onClose = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { _ ->
                        if (!sheetState.isVisible) {
                            currentOnDismissRequest()
                        }
                    }
            },
            content = content,
        )
    }
}

@Composable
private fun BottomSheetLayout(
    title: String,
    onClose: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = BottomSheetDefaults.colors

    Column(
        modifier = Modifier
            .padding(all = NdpaTheme.spacing.x2l),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                modifier = Modifier.weight(weight = 1f),
                style = BottomSheetDefaults.titleStyle,
                color = colors.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Box(
                modifier = Modifier
                    .size(size = NdpaTheme.sizes.tapTarget)
                    .clickable(
                        interactionSource = null,
                        indication = PressedIndication,
                        role = Role.Button,
                        onClick = onClose,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = NdpaIcons.Close,
                    contentDescription = stringResource(Res.string.sheet_close),
                    modifier = Modifier.size(size = NdpaTheme.sizes.icon),
                    tint = colors.close,
                )
            }
        }
        Spacer(modifier = Modifier.height(height = NdpaTheme.spacing.x2l))
        content()
    }
}

internal object BottomSheetDefaults {

    val shapes: BottomSheetShapes
        @Composable
        @ReadOnlyComposable
        get() = BottomSheetShapes(
            container = NdpaTheme.shapes.xl.copy(
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp),
            ),
        )

    val colors: BottomSheetColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            BottomSheetColors(
                container = surface,
                title = text,
                close = text,
                scrim = scrim,
            )
        }

    val titleStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.title
}

@Immutable
internal data class BottomSheetShapes(
    val container: Shape,
)

@Immutable
internal data class BottomSheetColors(
    val container: Color,
    val title: Color,
    val close: Color,
    val scrim: Color,
)

@Preview
@Composable
private fun BottomSheetPreview() {
    ThemedPreviews {
        BottomSheetLayout(
            title = "App usage access",
            onClose = { /* no-op */ },
        ) {
            Text(
                text = "To know which app is open. Data never leaves the device.",
                style = NdpaTheme.typography.body,
                color = NdpaTheme.colors.textMuted,
            )
            Spacer(modifier = Modifier.height(height = NdpaTheme.spacing.x2l))
            PrimaryButton(
                text = "Allow",
                onClick = { /* no-op */ },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
