package com.call.prestamoamigo.view.Persona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.prestamoamigo.data.repository.PersonasRepository
import com.call.prestamoamigo.model.Persona
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonaViewModel @Inject constructor(
    val personasRepository: PersonasRepository
): ViewModel() {

    var personas = personasRepository.GetLista()


    var personaId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var correo by mutableStateOf("")
    var direccion by mutableStateOf("")
    var prestamosTotales by mutableStateOf(0)
    var monto by mutableStateOf(0.0)

    fun Guardar(){
        viewModelScope.launch {
            personasRepository.Insertar(
                Persona(
                    personaId = personaId,
                    telefono = telefono,
                    nombre = nombre,
                    correo = correo,
                    direccion = direccion,
                    prestamosTotales = prestamosTotales
                )
            )
        }
    }

    fun GetMontoPrestamo(personaId: Int?): Double{
        if (personaId!=null){
            viewModelScope.launch {
                monto = personasRepository.GetMontoFromPrestamos(personaId)
            }
            return monto
        }else
            return monto
    }
}
