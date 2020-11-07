package android.example.com.rowcounter2

import android.app.Application
import android.example.com.rowcounter2.db.CountDataBaseDao
import android.example.com.rowcounter2.db.MyEnt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TitleViewModel( val databasedao: CountDataBaseDao, application: Application): AndroidViewModel(application) {

    //создаем лайвдата переменную для хранения последнего элемента таблицы, с которым мы должны работать
    private var mydbCount = MutableLiveData<MyEnt>()

    private var _count = MutableLiveData<Int>()
    val count : LiveData<Int>
    get() = _count

    //переменная для отображения всех записей из БД во вью. обновляется автоматически при помощи рум
    var counts: LiveData<List<MyEnt>> = databasedao.getAllCount()

    //val countdispLd = mydbCount.value

    init {

        initilizeMydbCount()

    }

    //Функция что показывает нам текущее значение счетчика при загрузке программы
    //определяет если в БД пусто, то создает новую строку с данными из ентити
    //если в БД етсь данные, то значение счета присваивается лайвдата переменной  _count, которая отображает счет пользователю на момент загрузки программы, также она наблюдается

    private fun initilizeMydbCount(){
        viewModelScope.launch {
            mydbCount.value= databasedao.getCount()

            if (mydbCount.value==null){
                databasedao.insert(MyEnt())
                //после того как вставили строку, зановоп рисвоим значение mydbCount
                mydbCount.value= databasedao.getCount()
            }
            //так как у нас вначале переменная инициадизируется нулем, сделал так, чтобы инициализировалось сохраненным знаением из бд
            //_count.value=mydbCount.value?.countNum
            mydbCount.value= databasedao.getCount()
            //сразу инициализируем нашу основную переменную лайвдаты, чтобы при запуске она равнялась записанному значению из БД. а потом
            // в самом низу внутри функции апдейта мы записывыаем в БД значение этой лайвдаты, так как прибавляем мы значение к нашей лайвдате _сcount
            _count.value=mydbCount.value!!.countNum

        }

    }



     fun plusOne(){
        _count.value = (_count.value)?.plus(1)
         //вызываем сопрограмму по обновлению значения в БД
         addPlusAtDB()

    }

  private fun addPlusAtDB(){
      viewModelScope.launch {

          //блок проверки наличия строки в случае ее удаления по нажатию кнопки Clear, если строку удалили, то создаем новую
          mydbCount.value= databasedao.getCount()

          if (mydbCount.value==null){
              databasedao.insert(MyEnt())
              //после того как вставили строку, зановоп рисвоим значение mydbCount
              mydbCount.value= databasedao.getCount()
          }
          //блок обновления значения счетчика в БД
          //а тут мы апдейтим нашу строку значением переменной  _count, та как выше мы по нажаьтию кнопки увеличиваем ее на один.
          // при каждом увеличении мы берем значение лайвдата _count  и записываем его в соответствующее поле БД
          mydbCount.value?.countNum=_count.value!!
          val newCount =mydbCount.value?: return@launch
          databasedao.update(newCount)


      }
  }
    //ну тут ясно, удялем строку
    fun clearData(){
        viewModelScope.launch {
        databasedao.clear()
        }
        _count.value=0
    }

}