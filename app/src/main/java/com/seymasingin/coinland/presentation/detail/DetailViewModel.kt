package com.seymasingin.coinland.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.source.remote.CoinService
import com.seymasingin.coinland.intent.CoinListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val coinService: CoinService
) : ViewModel() {

    private val _viewState = MutableStateFlow<DetailViewState>(DetailViewState.Idle)
    val viewState: StateFlow<DetailViewState> get() = _viewState

    fun dispatch(intent: CoinListIntent, id: String) {
        when (intent) {
            is CoinListIntent.Refresh -> {
                _viewState.value = DetailViewState.Loading(true)
                viewModelScope.launch {
                    try {
                        val coin = coinService.getCoinDetail(id)
                        _viewState.value = DetailViewState.Success(coin)

                    } catch (e: Exception) {
                        _viewState.value = DetailViewState.Error("An error occurred: ${e.message}")
                    }
                }
            }
        }
    }
}

sealed interface DetailViewState {
    data object Idle : DetailViewState
    data class Loading(val isAutomaticRefresh: Boolean) : DetailViewState
    data class Error(val message: String) : DetailViewState
    data class Success(val coin: CoinDetail) : DetailViewState
}

/*
suspend fun getSelectedCoin(coinId: String){
        _selectedCoin.value = coinService.getCoinDetail(coinId)
    }



    private val _selectedCoin: MutableStateFlow<CoinDetail?> = MutableStateFlow(null)
    val selectedCoin: StateFlow<CoinDetail?> = _selectedCoin
 */