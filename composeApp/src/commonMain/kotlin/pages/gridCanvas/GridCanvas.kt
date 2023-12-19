package pages.gridCanvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.sign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GridCanvasApp() {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var scale by remember { mutableStateOf(1f) }

    val gridCount = 20
    val lineCount = gridCount + 1
    var screenW by remember { mutableStateOf(0f) }
    var screenH by remember { mutableStateOf(0f) }
    val xUnit = screenW / gridCount
    val yUnit = screenH / gridCount
    val xList = Array(lineCount) {
        it * xUnit
    }
    val yList = Array(lineCount) {
        it * yUnit
    }

    Box(Modifier.fillMaxSize().padding(12.dp)) {
        Box(
            Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        offset = with(offset) { Offset(x + pan.x, y + pan.y) }
                    }
                }
                .onPointerEvent(PointerEventType.Scroll) {
                    val change = it.changes.first()
                    val delta = change.scrollDelta.y.toInt().sign
                    scale += delta / 100f
                }
                .scale(scale)
                .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
                .border(2.dp, Color.Blue, RectangleShape)
                .padding(12.dp)
        ) {
            Canvas(Modifier.fillMaxSize().background(Color.White)) {
                screenW = size.width
                screenH = size.height
                repeat(lineCount) {
                    drawLine(
                        Color.Black,
                        start = Offset(xList[it], 0f),
                        end = Offset(xList[it], screenH),
                        strokeWidth = 2f
                    )
                    drawLine(
                        Color.Black,
                        start = Offset(0f, yList[it]),
                        end = Offset(screenW, yList[it]),
                        strokeWidth = 2f
                    )
                }
            }
        }
    }
}