package ru.korolevss.main.ui.compose

import android.content.res.Configuration
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.systemBarsPadding
import extensions.round
import ru.korolevss.compose_general.common.ErrorDialog
import ru.korolevss.compose_general.common.FullScreenLoading
import ru.korolevss.compose_general.common.InsetAwareTopAppBar
import ru.korolevss.compose_general.common.LoadingContent
import ru.korolevss.compose_general.isScrolled
import ru.korolevss.compose_general.theme.CryptoCurrencyInfoApp
import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.R
import ru.korolevss.main.ui.MainState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    uiState: MainState,
    openCoinAsset: (String) -> Unit,
    openSearch: () -> Unit,
    openSettings: () -> Unit,
    onRefresh: () -> Unit,
    onLoadNewCoinAssets: () -> Unit,
    onErrorDismiss: () -> Unit,
    mainScaffoldState: ScaffoldState
) {
    val scrollState = rememberLazyListState()
    Scaffold(
        scaffoldState = mainScaffoldState,
        snackbarHost = { SnackbarHost(hostState = it, modifier = Modifier.systemBarsPadding()) },
        topBar = {
            val title = stringResource(id = ru.korolevss.core.R.string.app_name)
            InsetAwareTopAppBar(
                title = {
                    Text(
                        title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 4.dp, top = 10.dp),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openSettings) {
                        Icon(
                            painter = painterResource(R.drawable.ic_settings),
                            contentDescription = stringResource(R.string.cd_open_settings),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = openSearch) {
                        Icon(
                            painter = painterResource(R.drawable.ic_serach),
                            contentDescription = stringResource(R.string.cd_open_search),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = if (!scrollState.isScrolled) 0.dp else 4.dp
            )
        }
    ) { innerPadding ->
        val paddingModifier = Modifier.padding(innerPadding)
        LoadingContent(
            empty = uiState.initialLoad,
            emptyContent = { FullScreenLoading() },
            isLoading = uiState.isLoading,
            onRefresh = onRefresh,
            content = {
                MainScreenErrorAndContent(
                    coinAssets = uiState.coinAssets,
                    onLoadNewCoinAssets = onLoadNewCoinAssets,
                    openCoinAsset = openCoinAsset,
                    scrollState = scrollState,
                    modifier = paddingModifier
                )
            }
        )

        if (uiState.hasServererror || uiState.hasNoInternetError) {
            @StringRes val errorMessage = if (uiState.hasServererror) R.string.server_error
            else R.string.no_internet_connection_error
            val retryMessage = R.string.retry

            ErrorDialog(
                errorMessage = errorMessage,
                retryMessage = retryMessage,
                onErrorDismiss =onErrorDismiss,
                onRetry = onRefresh
            )
        }
    }
}

@Composable
private fun MainScreenErrorAndContent(
    coinAssets: List<CoinAsset>,
    onLoadNewCoinAssets: () -> Unit,
    openCoinAsset: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState
) {
    if (coinAssets.isNotEmpty()) {
        CoinAssetsList(
            coinAssets = coinAssets,
            onLoadNewCoinAssets = onLoadNewCoinAssets,
            openCoinAsset = openCoinAsset,
            modifier = modifier,
            scrollState = scrollState
        )
    } else {
        Box(modifier.fillMaxSize()) { /* empty screen */ }
    }
}

@Composable
private fun CoinAssetsList(
    coinAssets: List<CoinAsset>,
    onLoadNewCoinAssets: () -> Unit,
    openCoinAsset: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = false
        )
    ) {
        items(coinAssets) { coinAsset ->
            CoinAssetRow(coinAsset = coinAsset, openCoinAsset = openCoinAsset)
            CoinAssetsDivider()
        }
        if (scrollState.firstVisibleItemIndex == coinAssets.size - 10) onLoadNewCoinAssets()
    }
}


@Composable
private fun CoinAssetRow(
    coinAsset: CoinAsset,
    openCoinAsset: (String) -> Unit
) {
    Column {
        Row(
            Modifier
                .clickable(onClick = { openCoinAsset(coinAsset.id) })
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter("https://cryptologos.cc/logos/${coinAsset.id}-${coinAsset.symbol.lowercase()}-logo.png?v=014"),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp, 40.dp)
                    .clip(MaterialTheme.shapes.small)
                    .padding(end = 16.dp),
                alignment = Alignment.Center
            )
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(end = 16.dp)
            ) {
                Text("${coinAsset.rank}. ${coinAsset.symbol}", style = MaterialTheme.typography.subtitle1)
                Text(coinAsset.name, style = MaterialTheme.typography.body2)
            }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text("${coinAsset.priceUsd.round()} USD", style = MaterialTheme.typography.body1)
                Text(
                    "${coinAsset.changePercent24Hr.round()} % per day",
                    style = MaterialTheme.typography.body2,
                    color = if (coinAsset.changePercent24Hr < 0) Color.Red else Color.Green
                )
            }
        }
    }
}

@Composable
private fun CoinAssetsDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Preview("Home screen")
@Preview("Home screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    val coinAssets =
        List(10) {
            CoinAsset(
                id = "${it}-bitcoin",
                symbol = "${it}-btc",
                name = "${it}-bitcoin",
                priceUsd = it * 20.0,
                changePercent24Hr = -it * 0.675
            )
        }

    CryptoCurrencyInfoApp {
        MainScreen(
            uiState = MainState(coinAssets = coinAssets),
            openCoinAsset = { /*TODO*/ },
            openSearch = { /*TODO*/ },
            openSettings = { /*TODO*/ },
            onRefresh = { /*TODO*/ },
            onLoadNewCoinAssets = { /*TODO*/ },
            onErrorDismiss = { /*TODO*/ },
            mainScaffoldState = rememberScaffoldState()
        )
    }
}

@Preview("Home screen")
@Preview("Home screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCoinAssetRow() {
    val coinAsset = CoinAsset(
        id = "bitcoin",
        symbol = "btc",
        name = "bitcoin",
        priceUsd = 20.0774848930940403,
        changePercent24Hr = -0.6754474374747374433487343
    )
    CryptoCurrencyInfoApp {
        CoinAssetRow(coinAsset = coinAsset, openCoinAsset = { /*TODO*/ })
    }
}

