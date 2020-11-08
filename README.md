# myRepo
Добрый день.
В данном репозитории расположен проект "RowCounter2". Проект предназначен для демонстрации моих текущих навыков в Android разработке на Kotlin.
Описание:
Приложение состоит из двух фрагментов (используется constraintlayout). В титульном фрагменте содержится счетчик, по нажатию на кнопку "+" в оостветствующем поле отображается значение счета. Текущее значение сохраняется в БД ROOM. ниже расположено сервисное текстовое поле отображающее строку из БД (оно было сделано больше для самоконтроля, но решил оставить), а также кнопка удаления данных из БД. При достижении значения счета в 20 - автоматически выполняется переход во второй фрагмент, где пользователь может продолжить увеличивать счет. После чего по нажатию соответствующей кнопки он сможет вернуться обратно в первый фрагмент.
Реализация:
Фрагменты используют макет constraintlayout. Приложение выполнено с исползованием "Data Binding", "Save Args", "Navigation", используется паттерн MVVM, "ROOM DataBase", "LiveData", "Observers", инкапсуляция. При запуске приложения, во ViewModel классе происходит инициализация наличия данных в БД, если пусто, то мы создаем новую строку, а также выполняется инициализация LiveData-переменной отображающей счет. При нажатии на кнопку плюс срабатывает android:onClick событие вызывающее соответствующую функцию описанную во ViewModel классе. Функция увеличения счета проверяет есть ли данные в БД, так как мы ранее могли удалить все данные функцией очистки, если пусто то создает новую строку, затем увеличивается значение в БД на значение содержащееся в LiveData. Отображение значения счета реализовано путем наблюдения за изменениеv инкапсулированной LiveData-переменной содержаей счет. Так же есть второй наблюдатель, следящий за этой же LiveData-переменной и при достижении ее значения равному 20 выполняет переход во второй фрагмент с использованием SaveArgs и действий навигации - попутно передавая аргумент нашего счета и второй аргумент (var tt: Int = 3, передачей этого аргумента я демонстрирую передачу данных между фрагментами не только через ViewModel/ViewModelFactory). Я прекрасно понимаю что два наблюдателя можно объединить в один, но оставлю так для наглядности.                      Переходя во второй фрагмент мы через ViewModelFactory передаем значение счета LiveData, а также передаем второй аргумент используя fromBundle напрямую во фрагмент. Во втором фрагменте выполняются все теже действия с тображением и записью в БД как и в первом. По нажатию соответствующей кнопки можно вернуться обратно в первый фрагмент.
Код сопровожден моими комментариями.