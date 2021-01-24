package detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repository.Models

class DetailViewModel(application: Application, models: Models): AndroidViewModel(application) {



    //getting data from fragment
    private val _models = MutableLiveData<Models>()
    val models: LiveData<Models>
    get() = _models

    //observable val for opening youtube
    private val _openYouTuBe = MutableLiveData<Boolean>(false)
    val openYouTuBe : LiveData<Boolean>
    get() = _openYouTuBe


    init {
        _models.value = models
    }

    //open youTuBe
    fun openVideo (){
        _openYouTuBe.value=true
    }
    fun videoOpenned(){
        _openYouTuBe.value = false
    }



}