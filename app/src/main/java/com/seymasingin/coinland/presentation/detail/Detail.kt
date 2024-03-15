package com.seymasingin.coinland.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.intent.CoinListIntent

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Detail(
    viewModel: DetailViewModel,
    selectedCoin: CoinDetail
){

    LaunchedEffect(key1 = "detail") {

            viewModel.dispatch(CoinListIntent.Refresh, selectedCoin.id)

    }

    val viewState by viewModel.viewState.collectAsState()


    Scaffold {innerPadding ->
        when (viewState) {

            is DetailViewState.Idle -> Unit

            is DetailViewState.Loading -> {
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

            is DetailViewState.Error -> {
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

            is DetailViewState.Success -> {
                Column(modifier = Modifier.padding(innerPadding)){
                    Box(modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)){

                        LineChart(
                            modifier = Modifier.size(width = 48.dp, height = 29.dp),
                            data = selectedCoin.sparklineData,
                            graphColor = Color.Blue,
                            showDashedLine = true
                        )
                        }
                    }
                    Row(){
                        GlideImage(
                            modifier = Modifier.size(40.dp),
                            model = selectedCoin.image,
                            contentDescription = ""
                        )
                        Column {
                            Text(
                                text = selectedCoin.name,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = selectedCoin.symbol,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Column {
                            Text(
                                text = selectedCoin.price.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(
                                    start = 4.dp,
                                    end = 4.dp,
                                    top = 1.dp,
                                    bottom = 1.dp
                                ),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = selectedCoin.priceChangePercentage,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 1.dp)
                                    .align(Alignment.End),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                maxLines = 1
                            )
                        }
                    }
                    Column {
                        Row{
                            Text(text = "Market Cap")
                            Text(text = selectedCoin.marketCapRank.toString(),)
                        }
                        Row {
                            Text(text = "Liquidity Score")
                            Text(text = selectedCoin.liquidityScore.toString())
                        }
                        Row {
                            Text(text = "Highest Price 24h")
                            Text(text = selectedCoin.high24h)
                        }
                        Row {
                            Text(text = "Lowest Price 24h")
                            Text(text = selectedCoin.low24h)
                        }
                    }
                }
            }
        }
    }
