package com.example.govermentapp.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.govermentapp.domain.models.GovernmentInstitution
import com.example.govermentapp.domain.usescase.FilterGovernmentInstitutionUseCase
import com.example.govermentapp.domain.usescase.GetGovernmentInstitutionsUseCase
import com.example.govermentapp.domain.usescase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGovernmentInstitutions: GetGovernmentInstitutionsUseCase,
    private val filterGovernmentInstitution: FilterGovernmentInstitutionUseCase,
    private val getLocation: GetLocationUseCase
    ) : ViewModel() {

    private val _organizationsStateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val organizationsStateFlow:StateFlow<HomeState> = _organizationsStateFlow

    private val _organizationLiveData = MutableLiveData<GovernmentInstitution>()
    val organizationLiveData:LiveData<GovernmentInstitution> get() = _organizationLiveData

    init {
        viewModelScope.launch {
            _organizationsStateFlow.value = HomeState.Loading
            getGovernmentInstitutions()
                .catch { exception ->
                    _organizationsStateFlow.value = HomeState.Error(exception)
                }
                .collect{
                    _organizationsStateFlow.value = HomeState.Success(it)
                }
        }
    }

    fun shareGovernmentInstitution(governmentInstitution: GovernmentInstitution) {
        viewModelScope.launch {
            //Mandar llamar loader antes
            val location:Location? = getLocation()
            if(location != null){
                governmentInstitution.latitude = location.latitude
                governmentInstitution.longitude = location.longitude
                _organizationLiveData.value = governmentInstitution
            }
        }
    }

    fun searchGovernmentInstitution(query: String) {
        viewModelScope.launch {
            filterGovernmentInstitution(query)
                .catch { exception ->
                    _organizationsStateFlow.value = HomeState.Error(exception)
                }
                .collect{
                _organizationsStateFlow.value = HomeState.Success(it)
            }
        }
    }
}

sealed class HomeState {
    object Loading:HomeState()
    data class Error(val msg:Throwable):HomeState()
    data class Success(val data:List<GovernmentInstitution>):HomeState()
}
sealed class HomeUiAction {
    object DetailItem:HomeUiAction()
    data class ShareItem(val governmentInstitution: GovernmentInstitution):HomeUiAction()
}