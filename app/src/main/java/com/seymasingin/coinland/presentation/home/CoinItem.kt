package com.seymasingin.coinland.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.seymasingin.coinland.R
import com.seymasingin.coinland.data.model.CoinUI

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CoinItem(
    coin: CoinUI,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 18.dp, bottom = 18.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${coin.id}") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier.size(30.dp),
                model = coin.image,
                contentDescription = ""
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
            ) {
                Text(
                    text = coin.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if(coin.priceChangePercentage.toDouble()>0) {
                    painterResource(R.drawable.ic_up)

                }else{
                    painterResource(R.drawable.ic_down)
                },
                contentDescription = "",

                modifier = Modifier.size(35.dp).padding(end= 8.dp),
                if (coin.priceChangePercentage.toDouble() > 0) {
                    colorResource(id = R.color.upcolor)
                } else {
                    colorResource(id = R.color.downcolor)
                }

            )
            Text(
                text = coin.price.toString() ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = 4.dp,
                    end = 4.dp,
                    top = 1.dp,
                    bottom = 1.dp
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

    }
    Divider()
}


