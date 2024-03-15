package com.seymasingin.coinland.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.source.remote.CoinService
import com.seymasingin.coinland.intent.CoinListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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

    private val _selectedCoin=  MutableStateFlow<List<CoinDetail>>(emptyList())
    val selectedCoin: StateFlow<List<CoinDetail>> = _selectedCoin

    fun getSelectedCoin(id: String){
        viewModelScope.launch {
            _selectedCoin.value = coinService.getCoinDetail(id)
        }
    }
}

sealed interface DetailViewState {
    data object Idle : DetailViewState
    data class Loading(val isAutomaticRefresh: Boolean) : DetailViewState
    data class Error(val message: String) : DetailViewState
    data class Success(val coin: List<CoinDetail>) : DetailViewState
}