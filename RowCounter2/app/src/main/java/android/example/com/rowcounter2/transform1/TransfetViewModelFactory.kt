package android.example.com.rowcounter2.transform1

import android.app.Application
import android.example.com.rowcounter2.db.CountDataBaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TransfetViewModelFactory(private val vviewcount2: Int,
                               private val application: Application,
                               private val dataSource: CountDataBaseDao): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransfetViewModel::class.java)){
            return TransfetViewModel(vviewcount2, application,dataSource) as T}
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}