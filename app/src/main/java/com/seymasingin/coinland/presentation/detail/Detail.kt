package com.seymasingin.coinland.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.seymasingin.coinland.R
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.intent.CoinListIntent

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Detail(
    viewModel: DetailViewModel,
    selectedCoin: List<CoinDetail>,
    navController: NavController
) {

    LaunchedEffect(key1 = "detail") {
        viewModel.dispatch(CoinListIntent.Refresh, selectedCoin.firstOrNull()?.id ?: "")
    }

    val viewState by viewModel.viewState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        when (viewState) {

            is DetailViewState.Idle -> Unit

            is DetailViewState.Loading -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
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
                ) {
                    Text(
                        text = "An error occurred",
                        color = Color.Black,
                    )
                }
            }

            is DetailViewState.Success -> {
                if (selectedCoin.isNotEmpty()) {
                    val sn = selectedCoin[0]
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(10.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    painterResource(
                                        id = R.drawable.ic_back),
                                    contentDescription = "",
                                    Modifier.size(30.dp),
                                    tint = colorResource(id = R.color.textcolor)
                                )
                            }
                            IconButton(
                                onClick = { /*TODO*/ }
                            ) {
                                Icon(painterResource(
                                    id = R.drawable.ic_alarm) ,
                                    contentDescription = "", Modifier.size(30.dp))
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 25.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier.clickable {
                                    showBottomSheet = true
                                },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.DarkGray,
                                ),
                                shape = RoundedCornerShape(3.dp),
                            ) {
                                Text(
                                    modifier = Modifier.padding(start=8.dp,end=8.dp, top=5.dp, bottom=5.dp ),
                                    text = "BUY",
                                    fontSize = 30.sp,
                                    maxLines = 1
                                )
                            }
                            if (showBottomSheet) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        showBottomSheet = false
                                    },
                                    sheetState = sheetState
                                ) {
                                    Box(modifier = Modifier.size(80.dp)) {

                                    }
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Column(
                                    modifier = Modifier.padding(end= 15.dp),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = sn.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        maxLines = 1,
                                        color = colorResource(id = R.color.textcolor)
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = sn.symbol,
                                        fontSize = 20.sp,
                                        color = colorResource(id = R.color.textcolor)
                                    )
                                }
                                GlideImage(
                                    modifier = Modifier
                                        .size(50.dp),
                                    model = sn.image,
                                    contentDescription = ""
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(25.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                if(sn.priceChangePercentage.toDouble()>0) {
                                    painterResource(R.drawable.ic_up)

                                }else{
                                    painterResource(R.drawable.ic_down)
                                },
                                contentDescription = "",

                                modifier = Modifier.size(150.dp),
                                if (sn.priceChangePercentage.toDouble() > 0) {
                                    colorResource(id = R.color.upcolor)
                                } else {
                                    colorResource(id = R.color.downcolor)
                                }

                            )
                            Text(
                                text = "$ ${sn.price}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 1.dp),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                fontSize = 25.sp,
                                maxLines = 1,
                                color = colorResource(id = R.color.textcolor)
                            )
                        }
                        Text(
                            modifier= Modifier.padding(top= 15.dp),
                            text = "STATISTICS",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray,
                        ),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Price Change Percentage",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "% ${sn.priceChangePercentage}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray,
                        ),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Market Cap",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "$ ${sn.marketCap}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "All Time High Price",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "$ ${sn.ath}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "All Time Low Price",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "$ ${sn.atl}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Highest Price 24h",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "$ ${sn.high24h}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Card(colors = CardDefaults.cardColors(
                            contentColor = Color.Gray),
                            modifier = Modifier.padding(top=15.dp),
                            shape = RoundedCornerShape(3.dp)
                        ){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Lowest Price 24h",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "$ ${sn.low24h}",
                                    color = colorResource(id = R.color.textcolor),
                                    fontSize = 18.sp
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}





