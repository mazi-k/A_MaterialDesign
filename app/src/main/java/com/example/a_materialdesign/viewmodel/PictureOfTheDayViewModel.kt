package com.example.a_materialdesign.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a_materialdesign.BuildConfig
import com.example.a_materialdesign.model.PictureOfTheDayRetrofitImpl
import com.example.a_materialdesign.model.PictureOfTheDayServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {

    fun getLiveDataForViewToObserve() = liveDataForViewToObserve
    fun sendServerRequest(){
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        retrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    private val callback = object : Callback<PictureOfTheDayServerResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayServerResponseData>,
            response: Response<PictureOfTheDayServerResponseData>
        ) {
            if(response.isSuccessful){
                response.body()?.let {
                    liveDataForViewToObserve.postValue(AppState.Success(it))
                }
            }else{
                //TODO HW
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayServerResponseData>, t: Throwable) {
            //TODO HW
        }

    }
}