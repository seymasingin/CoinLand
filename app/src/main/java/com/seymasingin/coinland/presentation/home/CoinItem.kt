package com.seymasingin.coinland.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.seymasingin.coinland.R
import com.seymasingin.coinland.common.LineChart
import com.seymasingin.coinland.data.model.CoinList
import com.seymasingin.coinland.data.model.CoinUI
import com.seymasingin.coinland.data.model.DataPoint

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CoinItem(
    coin: CoinUI
){
    Card(
        onClick = {
        },
        colors = CardDefaults.cardColors(
            contentColor = Color.Gray
        ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column{
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                GlideImage(model = coin.imageUrl, contentDescription = "")
            }

            Column(
                modifier = Modifier.padding(start=10.dp).weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = coin.name,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = coin.price,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 1.dp, bottom = 1.dp),
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
            ) {
                Row{
                    LineChart(
                        modifier = Modifier.size(width = 48.dp, height = 29.dp),
                        data = coin.sparklineData,
                        graphColor = coin.trendColor,
                        showDashedLine = true
                    )
                }
                }
            }
        }
}

@Composable
@Preview
fun CoinItemPreview(){
    CoinItem(coin = CoinUI(
        "0",
        "BTC",
        "Bitcoin",
        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        trendColor = colorResource(id = R.color.chartColor),
        arrayListOf(
            DataPoint(10.0, "X1", "Y1"),
            DataPoint(20.0, "X2", "Y2"),
            DataPoint(30.0, "X3", "Y3"),
        ),
        "666"
    ))
}