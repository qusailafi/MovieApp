package com.example.movie.presenter.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.BuildConfig
import com.example.movie.domain.usecase.MoviesUseCase
import com.example.movie.utils.Resource
import com.example.movie.domain.entity.MoviesResponse
import com.example.movie.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
// HiltViewModel its  annotaion allow to  hilt to inject the viewModel.
//MovieViewModel its recived data from useCase and update the data to a Ui .
@HiltViewModel
class MovieViewModel @Inject constructor(val moviewUseCase: MoviesUseCase) : ViewModel() {
    lateinit var popularityResponse: MutableStateFlow<Resource<MoviesResponse>>//
    lateinit var revenueResponse: MutableStateFlow<Resource<MoviesResponse>>
    lateinit var topRatedResponse: MutableStateFlow<Resource<MoviesResponse>>
    var quires = HashMap<String, String>()
    var isLoadingTopRated: Boolean = false
    var isPopulateLoading: Boolean = false
    var isRevenueLoading: Boolean = false
    fun getPopularity(populatePageNUmber: Int): Flow<Resource<MoviesResponse>> {
        //will called movie use case and return a response from useCase
        HashMap<String, String>()
        quires.put(Utils.API_KEY, BuildConfig.ApiKey)
        quires.put(Utils.FILLTER_KEY, Utils.POPULARITY)
        quires.put(Utils.PAGE_KEY, populatePageNUmber.toString())
        popularityResponse = MutableStateFlow(Resource.loading(null))
        viewModelScope.launch {
            moviewUseCase(quires).collect {
                popularityResponse.value = it
            }
        }

        return popularityResponse
    }

    fun setTopRatedLoading(isLoading: Boolean) {
        this.isLoadingTopRated = isLoading
    }

    fun setPopUlateLoading(isLoading: Boolean) {
        this.isPopulateLoading = isLoading
    }

    fun isPopulateLoad(): Boolean {
        return isPopulateLoading
    }
    fun setRevenue(isLoading:Boolean){
        isRevenueLoading=isLoading
    }
    fun isRevenueLoad():Boolean{
        return isRevenueLoading
    }
    fun isTopRatedLoad(): Boolean {
        return isLoadingTopRated
    }

    fun isLastPage(pageSize: Int, pageNumber: Int): Boolean {
        if (pageSize == pageNumber) {
            return true
        }
        return false
    }
    fun getRevenue(revenuePageNumber: Int): Flow<Resource<MoviesResponse>> {
        //will called movie use case and return a response from useCase
        HashMap<String, String>()
        quires = HashMap()
        quires.put(Utils.API_KEY, BuildConfig.ApiKey)
        quires.put(Utils.FILLTER_KEY, Utils.REVENUE)
        quires.put(Utils.PAGE_KEY, revenuePageNumber.toString())
        revenueResponse = MutableStateFlow(Resource.loading(null))
        viewModelScope.launch {
            moviewUseCase(quires).collect {
                revenueResponse.value = it
            }
        }
        return revenueResponse
    }
    fun getTopRted(pageNumber: Int): Flow<Resource<MoviesResponse>> {
        //will called movie use case and return a response from useCase
        topRatedResponse = MutableStateFlow(Resource.loading(null))
        HashMap<String, String>()
        quires.put(Utils.PAGE_KEY, pageNumber.toString())

        quires.put(Utils.API_KEY, BuildConfig.ApiKey)
        quires.put(Utils.FILLTER_KEY, Utils.TOPRATED)
        viewModelScope.launch {
            moviewUseCase(quires).collect {
                topRatedResponse.value = it
            }
        }
        return topRatedResponse
    }

}