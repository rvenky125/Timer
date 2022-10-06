package com.example.timer

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimeRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isTimeRunning, key2 = currentTime ,block = {
        Log.d("myTag", "block")
        if (currentTime > 0 && isTimeRunning) {
            delay(50L)
            currentTime -= 50L
            value = currentTime / totalTime.toFloat()
            Log.d("myTag", "$currentTime $value")
        }
    })

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.onSizeChanged {
            size = it
        }
    ) {
        Canvas(
            modifier = modifier
        ) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimeRunning = true
                }
                else isTimeRunning = !isTimeRunning
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimeRunning || currentTime <= 0L) Color.Green else Color.Red
            )
        ) {
            Text(text = if (isTimeRunning && currentTime > 0L) "Stop" else if (!isTimeRunning && currentTime > 0L) "Start" else "Restart")
        }
    }
}


@Composable
fun FillTimer(
    totalTime: Long,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimeRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isTimeRunning, key2 = currentTime ,block = {
        Log.d("myTag", "block")
        if (currentTime > 0 && isTimeRunning) {
            delay(50L)
            currentTime -= 50L
            value = currentTime / totalTime.toFloat()
            Log.d("myTag", "$currentTime $value")
        }
    })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.onSizeChanged {
                size = it
            }
        ) {
            Canvas(
                modifier = modifier
            ) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -90f,
                    sweepAngle = -360f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Fill
                )

                drawArc(
                    color = activeBarColor,
                    startAngle = -90f,
                    sweepAngle = 360f * value,
                    useCenter = true,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Fill
                )
            }

            Text(
                text = (currentTime / 1000L).toString(),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimeRunning = true
                }
                else isTimeRunning = !isTimeRunning
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimeRunning || currentTime <= 0L) Color.Green else Color.Red
            )
        ) {
            Text(text = if (isTimeRunning && currentTime > 0L) "Stop" else if (!isTimeRunning && currentTime > 0L) "Start" else "Restart")
        }
    }
}