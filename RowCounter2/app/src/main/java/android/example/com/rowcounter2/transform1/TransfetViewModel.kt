package android.example.com.rowcounter2.transform1

import android.app.Application
import android.example.com.rowcounter2.db.CountDataBaseDao
import android.example.com.rowcounter2.db.MyEnt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransfetViewModel( viewcount2:Int, application: Application, val databasedao: CountDataBaseDao): AndroidViewModel(application) {
    //присваиваем входящий аргумент переменной
    var count = viewcount2

    //переменная, в которую селектим актуальное значение счетчика из БД
    private var mydbCountF = MutableLiveData<MyEnt>()

    //лайвдата переменная отвечающая за отображение счетчика в UI
    private var _count2 = MutableLiveData<Int>()
    val count2 : LiveData<Int>
        get() = _count2

    init {
        _count2.value=count //при инициализации значение входящиего аргумента присваиваем лайвдате отображающей счет

        initilizeMydbCount()
    }

    private fun initilizeMydbCount(){
        viewModelScope.launch {

            mydbCountF.value= databasedao.getCount()

        }
    }

    fun plusOne2(){
        _count2.value = (_count2.value)?.plus(1)
        addPlusAtDB()
    }

    private fun addPlusAtDB(){
        viewModelScope.launch {
            //а тут мы апдейтим нашу строку значением переменной  _count, та как выше мы по нажаьтию кнопки увеличиваем ее на один.
            // при каждом увеличении мы берем значение лайвдата _count  и записываем его в соответствующее поле БД
            mydbCountF.value?.countNum=_count2.value!!
            val newCount =mydbCountF.value?: return@launch
            databasedao.update(newCount)


        }
    }

}