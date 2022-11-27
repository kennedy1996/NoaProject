package com.noaproject.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noaproject.entity.NoaDtos
import com.noaproject.repository.NoaRepository
import kotlinx.coroutines.launch

class NoaViewModel: ViewModel() {

    private val repository = NoaRepository()
    private var noaResult = MutableLiveData<List<NoaDtos.TablesNew>>()

    fun searchNoaData() {
        viewModelScope.launch {
            noaResult.value=repository.convertMoney()
        }
    }

    fun getNoaData(): MutableLiveData<List<NoaDtos.TablesNew>> {
        return noaResult
    }
}