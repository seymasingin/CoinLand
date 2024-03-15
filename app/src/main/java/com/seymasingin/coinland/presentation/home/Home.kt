package com.seymasingin.coinland.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.intent.CoinListIntent

@Composable
fun Home(
    viewModel: HomeViewModel,
    navController: NavController,
) {
    LaunchedEffect(key1 = "home") {
        viewModel.dispatch(CoinListIntent.Refresh)
    }

    val viewState by viewModel.viewState.collectAsState()

    Scaffold{ innerPadding ->
        when (val state = viewState) {

            is CoinListViewState.Idle -> Unit

            is CoinListViewState.Loading -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

            is CoinListViewState.Error -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = "An error occurred",
                        color = Color.Black,
                    )
                }
            }

            is CoinListViewState.Success -> {
                LazyColumn(
                    contentPadding = innerPadding,
                    state = rememberLazyListState(),
                ) {
                items(state.coins) { coin ->
                    if (coin != null) {
                        CoinItem(coin = coin, navController)
                    }
                }
                }
            }
        }
    }
}
