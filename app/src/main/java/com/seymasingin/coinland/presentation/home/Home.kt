package com.seymasingin.coinland.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Home( navController: NavController){

    Scaffold() {innerPadding->
        LazyColumn(
            contentPadding = innerPadding
        ){
            items(){item ->
                CoinItem(){
                    goToCoinDetail(item)
                }

            }
        }
    }
}