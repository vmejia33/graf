package com.himanshoe.chartysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.area.AreaChart
import com.himanshoe.charty.area.model.AreaData
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.bubble.BubbleChart
import com.himanshoe.charty.bubble.model.BubbleData
import com.himanshoe.charty.circle.CircleChart
import com.himanshoe.charty.circle.model.CircleData
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.ComposeList
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.model.LineData
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.config.PieChartDefaults
import com.himanshoe.charty.pie.model.PieData
import com.himanshoe.charty.point.PointChart
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                ChartContent(Modifier.fillMaxSize())
            }
        }
    }

    private fun generateMockBubbleData(count: Int = 10): List<BubbleData> {
        val mockData = mutableListOf<BubbleData>()
        for (i in 1..count) {
            val xValue = "X$i"
            val yValue = Random.nextFloat() * 100
            val volumeSize = Random.nextFloat() * 100
            val bubbleData = BubbleData(xValue, yValue, volumeSize)
            mockData.add(bubbleData)
        }
        return mockData
    }

    private val data = listOf(
        PieData(30f, "Category A", Color.Blue),
        PieData(20f, "Category B", Color.Red),
        PieData(13f, "Category C", Color.Green),
        PieData(10f, "Category C", Color.Black),
    )
    private val circleData = listOf(
        CircleData(30f, "Category A", color = Color.Blue),
        CircleData(20f, "Category B", Color.Red),
        CircleData(10f, "Category C", Color.Green),
        CircleData(50f, "Category C", Color.Black),
    )
    private val bardata = listOf(
        BarData(10f, "Category A", color = Color.Red),
        BarData(20f, "Category B", color = Color.Blue),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(40f, "Category C", color = Color.Blue),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(30f, "Category C", color = Color.Green),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(50f, "Category C", color = Color.Blue),
        BarData(20f, "Category C", color = Color.Yellow),
        BarData(50f, "Category C", color = Color.Blue),
    )

    @Composable
    fun MyScreen() {
        val dataSeries = listOf(
            AreaData(
                points = listOf(0.5f, 0.8f, 0.6f, 0.9f, 0.7f, 0.4f),
                xValue = "Item 1",
                color = Color.Yellow
            ),
            AreaData(
                xValue = "Item 2",
                points = listOf(0.33f, 0.6f, 0.93f, 0.7f, 0.9f, 1.5f),
                color = Color.DarkGray
            ),
            AreaData(
                xValue = "Item 3",
                points = listOf(0.3f, 0.6f, 0.4f, 0.7f, 0.9f, 0.3f),
                color = Color.Red
            ),
        )
        val maxValue = 1.0f

        Box(Modifier.fillMaxSize()) {
            AreaChart(
                modifier = Modifier
                    .size(400.dp),
                areaData = ComposeList(
                    dataSeries
                )
            )
        }
    }

    @Composable
    private fun ChartContent(modifier: Modifier = Modifier) {
        LazyColumn(modifier) {
            item {
                MyScreen()
            }
            item {
                BubbleChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    dataCollection = ChartDataCollection(generateMockBubbleData())
                )
            }
            item {
                BarChart(
                    dataCollection = ChartDataCollection(bardata),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
            }
            item {
                CircleChart(
                    modifier = Modifier.size(400.dp),
                    dataCollection = ChartDataCollection(circleData),
                )
            }
            item {
                PieChart(
                    pieChartConfig = PieChartDefaults.defaultConfig().copy(donut = false),
                    dataCollection = ChartDataCollection(data),
                    modifier = Modifier.wrapContentSize()
                )
            }
            item {
                PieChart(
                    dataCollection = ChartDataCollection(data),
                    modifier = Modifier.wrapContentSize()
                )
            }
            item {
                CurveLineChart(
                    dataCollection = ChartDataCollection(generateMockLineDataList()),
                    modifier = Modifier
                        .size(450.dp),
                )
            }
            item {
                LineChart(
                    dataCollection = ChartDataCollection(generateMockLineDataList()),
                    modifier = Modifier
                        .size(450.dp),
                )
            }
            item {
                PointChart(
                    dataCollection = ChartDataCollection(generateMockLineDataList()),
                    modifier = Modifier
                        .size(450.dp),
                )
            }
        }
    }

    private fun generateMockLineDataList(): List<LineData> {
        return listOf(
            LineData(0F, "Jan"),
            LineData(10F, "Feb"),
            LineData(05F, "Mar"),
            LineData(50F, "Apr"),
            LineData(55F, "May"),
            LineData(03F, "June"),
            LineData(9F, "July"),
            LineData(40F, "Aug"),
            LineData(60F, "Sept"),
            LineData(33F, "Oct"),
            LineData(11F, "Nov"),
        )
    }
}
