package android.example.com.rowcounter2

import android.example.com.rowcounter2.databinding.FragmentTitleBinding
import android.example.com.rowcounter2.db.RowCountDB
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class TitleFragment : Fragment() {
    private lateinit var ttitleViewModel: TitleViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding =DataBindingUtil.inflate(
            inflater,R.layout.fragment_title, container, false)

        val application = requireNotNull(this.activity).application
        //ссылка на наш источник данных
        val dataSource= RowCountDB.getInstance(application).countDataBaseDao

        val viewModelFactory= TitleViewModelFactory(dataSource, application)

        ttitleViewModel=ViewModelProvider(this, viewModelFactory).get(TitleViewModel::class.java)

        binding.xmlttitleViewModel=ttitleViewModel

        //наблюдатель отображающий счет
        ttitleViewModel.count.observe(viewLifecycleOwner, Observer { newCount ->
            binding.countDisplay.text = newCount.toString()
        })

        //используется для демонстрации передачи значения в качестве аргумента  в другой фрагмент через бандл
        var tt: Int = 3

        //переход в другой фрагмент по наблюдателю
        ttitleViewModel.count.observe(viewLifecycleOwner, Observer {newCount2 ->
            if (newCount2==20){
                this.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToTransformtest(newCount2,tt))
            }
        })


        binding.setLifecycleOwner(this)
        return binding.root
    }

    }