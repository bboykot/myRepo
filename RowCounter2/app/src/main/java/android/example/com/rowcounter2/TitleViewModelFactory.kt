package android.example.com.rowcounter2

import android.app.Application
import android.example.com.rowcounter2.db.CountDataBaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TitleViewModelFactory(
    private val dataSource: CountDataBaseDao,
    private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TitleViewModel::class.java)) {
            return TitleViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}