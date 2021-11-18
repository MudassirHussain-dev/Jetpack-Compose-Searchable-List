package dev.hmh.jetpackcomposetoturial.convas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CanvasRectangleShape() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        val w = 500f
        val h = 500f

        drawRect(
            color = Color.Blue,
            size = Size(w, h),
            topLeft = Offset(x = center.x - w / 2, y = center.y - h / 2),
            style = Stroke(width = 6f)
        )
    }
}

@Composable
fun CanvasCircleShape() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        drawCircle(color = Color.Blue, radius = 200f)
        drawCircle(color = Color.Magenta, radius = 200f, center = Offset(x = 0f, y = 0f))
        drawCircle(
            radius = 300f, center = Offset(x = 300f, y = 300f), brush = Brush.linearGradient(
                colors = listOf(Color.Green, Color.Red, Color.Yellow)
            )
        )
        drawCircle(
            radius = 500f, center = Offset(x = 500f, y = 500f), brush = Brush.radialGradient(
                colors = listOf(Color.Green, Color.Red, Color.Yellow),
                radius = 350f,
                center = Offset(300f, 300f)
            )
        )
    }
}

@Composable
fun CanvasShapeDrawArc() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        drawArc(
            color = Color.Red,
            startAngle = 270f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(300f, 300f)
            //size = Size(size.width,size.height)
        )
    }
}

@Composable
fun CanvasPieCart(points: List<Float>, colorList: List<Color>) {
    val totalSum = points.sum()
    val percentageWeight = points.map { it ->
        it * 100 / totalSum
    }
    val percentageAngle = percentageWeight.map { it ->
        it * 360 / 100
    }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var startAngle = 270f
        drawArc(
            color = colorList[0],
            startAngle = startAngle,
            sweepAngle = percentageAngle[0],
            useCenter = true,
            size = Size(size.width, size.height)
        )
         startAngle  += percentageAngle[0]
        drawArc(
            color = colorList[1],
            startAngle = startAngle,
            sweepAngle = percentageAngle[1],
            useCenter = true,
            size = Size(size.width, size.height)
        )
    }
}

@Preview
@Composable
fun CanvasShapeReview() {

    CanvasPieCart(
        points = listOf(30f, 50f, 60f, 130f),
        colorList = listOf(Color.Green, Color.Black, Color.Magenta, Color.Red)
    )
}