package com.seymasingin.coinland.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seymasingin.coinland.data.model.CoinUI
import com.seymasingin.coinland.data.source.remote.CoinService
import com.seymasingin.coinland.intent.CoinListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinService: CoinService
) : ViewModel() {

    private val _viewState = MutableStateFlow<CoinListViewState>(CoinListViewState.Idle)
    val viewState: StateFlow<CoinListViewState> get() = _viewState

    fun dispatch(intent: CoinListIntent) {
        when (intent) {
            is CoinListIntent.Refresh -> {
                _viewState.value = CoinListViewState.Loading(true)
                viewModelScope.launch {
                    try {
                        val coins = coinService.getCoinsMarkets()
                        _viewState.value = CoinListViewState.Success(coins)

                    } catch (e: Exception) {
                        _viewState.value = CoinListViewState.Error("An error occurred: ${e.message}")
                    }
                }
            }
        }
    }
}

sealed interface CoinListViewState {
    data object Idle : CoinListViewState
    data class Loading(val isAutomaticRefresh: Boolean) : CoinListViewState
    data class Error(val message: String) : CoinListViewState
    data class Success(val coins: List<CoinUI?>) : CoinListViewState
}

