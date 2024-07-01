package com.example.museosoumaya.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museosoumaya.model.MObrasDeArte
import com.example.museosoumaya.model.ObraArte
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VMObrasDeArte:ViewModel() {
    val mLibros = MObrasDeArte()
    fun verObrasDeArte(callback: (List<ObraArte>?) -> Unit) {
        mLibros.obtenerObraDeArte(callback)
    }
}