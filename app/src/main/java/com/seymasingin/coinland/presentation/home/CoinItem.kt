package com.seymasingin.coinland.presentation.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.model.CoinUI
import com.seymasingin.coinland.navigation.Screen

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CoinItem(
    coin: CoinUI,
    navController: NavController
) {

    Card(
        onClick = {
            navController.navigate(Screen.Home.Detail(coin.id).route)
        },
        colors = CardDefaults.cardColors(
            contentColor = Color.Gray
        ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column {
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                GlideImage(
                    modifier = Modifier.size(21.dp),
                    model = coin.image,
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier.padding(start = 10.dp),
            ) {
                Text(
                    text = coin.name ?: "",
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
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
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

