package detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Models

class DetailViewModelfactory (
    private val application: Application,
    private val models: Models
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application, models) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}