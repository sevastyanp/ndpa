package com.panov.sevastyan.ndpa.core.ui.component.interaction

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.launch

private const val PressedAlpha: Float = 0.88f

internal object PressedIndication : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode =
        PressedIndicationNode(interactionSource)

    override fun equals(other: Any?): Boolean = this === other

    override fun hashCode(): Int = -1
}

private class PressedIndicationNode(
    private val interactionSource: InteractionSource,
) : Modifier.Node(),
    DrawModifierNode {

    private val activePresses = mutableListOf<PressInteraction.Press>()
    private val layerPaint = Paint().apply { alpha = PressedAlpha }
    private var pressed = false

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> activePresses.add(interaction)
                    is PressInteraction.Release -> activePresses.remove(interaction.press)
                    is PressInteraction.Cancel -> activePresses.remove(interaction.press)
                }
                val nowPressed = activePresses.isNotEmpty()
                if (nowPressed != pressed) {
                    pressed = nowPressed
                    invalidateDraw()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        if (!pressed) {
            drawContent()
            return
        }
        drawIntoCanvas { canvas ->
            canvas.saveLayer(size.toRect(), layerPaint)
            this@draw.drawContent()
            canvas.restore()
        }
    }
}
