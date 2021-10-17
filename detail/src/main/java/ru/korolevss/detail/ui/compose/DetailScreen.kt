package ru.korolevss.detail.ui.compose

import android.graphics.Paint
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import extensions.round
import extensions.toDateFormatString
import kotlinx.coroutines.launch
import ru.korolevss.compose_general.common.ErrorDialog
import ru.korolevss.compose_general.common.InsetAwareTopAppBar
import ru.korolevss.compose_general.isScrolled
import ru.korolevss.core.R
import ru.korolevss.detail.ui.DetailState
import kotlin.math.ceil
import kotlin.math.round

@ExperimentalPagerApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    uiState: DetailState,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
    onErrorDismiss: () -> Unit,
    scaffoldState: ScaffoldState
) {
    val scrollState = rememberLazyListState()
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = it, modifier = Modifier.systemBarsPadding()) },
        topBar = {
            InsetAwareTopAppBar(
                title = {
                    Text(
                        uiState.title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 4.dp, top = 10.dp),
                        textAlign = TextAlign.Start
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = if (!scrollState.isScrolled) 0.dp else 4.dp
            )
        }
    ) {
        val pagerState = rememberPagerState()
        val pages =
            listOf(stringResource(id = ru.korolevss.detail.R.string.tab_general), stringResource(id = ru.korolevss.detail.R.string.tab_markets))
        val scope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState
        ) { page ->
            if (page == 0) {
                CandlesLayout(
                    uiState = uiState,
                    lineColor = MaterialTheme.colors.onBackground
                )
            }
        }

        if (uiState.hasServererror || uiState.hasNoInternetError) {
            @StringRes val errorMessage = if (uiState.hasServererror) R.string.server_error
            else R.string.no_internet_connection_error
            val retryMessage = R.string.retry

            ErrorDialog(
                errorMessage = errorMessage,
                retryMessage = retryMessage,
                onErrorDismiss = onErrorDismiss,
                onRetry = onRefresh
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CandlesLayout(
    uiState: DetailState,
    lineColor: Color
) {
    val candlesSize = 30
    val candles = uiState.coinCandles.takeLast(candlesSize)
    val yMax = ceil(checkNotNull(candles.maxOfOrNull { candle -> candle.high }) { return } * 1.2)
    val yMin = round(checkNotNull(candles.minOfOrNull { candle -> candle.low }) { return } * 0.8)
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        val yPart = size.height / 4
        val xPart = size.width / candlesSize + 1
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        val textYPaint = Paint()
        textYPaint.textSize = 8.sp.toPx()
        val textXPaint = Paint()
        textXPaint.textSize = 8.sp.toPx()
        textXPaint.textAlign = Paint.Align.RIGHT

        List(5) { it }.forEach { index ->
            drawLine(
                start = Offset(x = 0.0f, y = index * yPart),
                end = Offset(x = size.width, y = index * yPart),
                color = lineColor,
                alpha = 0.5f,
                pathEffect = pathEffect
            )
            val yAxisText = yMax - ((yMax - yMin) / 4 * index)
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    yAxisText.round(2).toString(), 0.0f, index * yPart + textYPaint.textSize, textYPaint
                )
            }
        }
        val xAxisLastText = candles.last().period
            .toDateFormatString()

        drawIntoCanvas {
            it.nativeCanvas.drawText(
                xAxisLastText, candlesSize * xPart, size.height, textXPaint
            )
        }

        List(candlesSize) { it }.forEach { index ->
            drawLine(
                start = Offset(x = (index + 1) * xPart, y = 0.0f),
                end = Offset(x = (index + 1) * xPart, y = size.height),
                color = lineColor,
                alpha = 0.5f,
                pathEffect = pathEffect
            )
            val candle = candles[candles.lastIndex - index]

            val yHigh = (1 - (candle.high - yMin) / (yMax - yMin)) * size.height
            val yLow = (1 - (candle.low - yMin) / (yMax - yMin)) * size.height

            drawLine(
                start = Offset(x = (index + 1) * xPart, y = yHigh.toFloat()),
                end = Offset(x = (index + 1) * xPart, y = yLow.toFloat()),
                color = if (candle.close <= candle.open) Color.Green else Color.Red,
                strokeWidth = 2.dp.toPx()
            )
            val startRect = (1 - ((if (candle.close >= candle.open) candle.close else candle.open) - yMin) / (yMax - yMin)) * size.height
            val endRect = (1 - ((if (candle.close < candle.open) candle.close else candle.open) - yMin) / (yMax - yMin)) * size.height

            drawRect(
                color = if (candle.close <= candle.open) Color.Green else Color.Red,
                topLeft = Offset(x = (index + 1) * xPart - 4.dp.toPx(), y = startRect.toFloat()),
                size = Size(width = 8.dp.toPx(), height = (endRect - startRect).toFloat())
            )
        }
    }
}