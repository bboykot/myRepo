package android.example.com.rowcounter2.transform1

import android.example.com.rowcounter2.R
import android.example.com.rowcounter2.databinding.FragmentTransformtestBinding
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


class Transformtest : Fragment() {


    private lateinit var fragTransferViewModel : TransfetViewModel
    private lateinit var factoryViewModel:TransfetViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTransformtestBinding=DataBindingUtil.inflate(
            inflater,R.layout.fragment_transformtest, container, false)

        val application=  requireNotNull(this.activity).application

        val dataSource= RowCountDB.getInstance(application).countDataBaseDao

        //получаем аргументы в фактори
        factoryViewModel= TransfetViewModelFactory(TransformtestArgs.fromBundle(requireArguments()).countTransfer,application,dataSource)
        //создаем вьюмодел
        fragTransferViewModel = ViewModelProvider(this,factoryViewModel).get(TransfetViewModel::class.java)

        binding.xmlTransferViewModel=fragTransferViewModel

        //наблюдатель отображающий счет
        fragTransferViewModel.count2.observe(viewLifecycleOwner, Observer { newCount2->
            binding.transferCountdisp.text=newCount2.toString()
        })

        //получаем аргументы из бандла. мы их передали из другого фрагмента
        val args=TransformtestArgs.fromBundle(requireArguments())
        binding.argsTest.text=args.test.toString()


        binding.moveTotitle.setOnClickListener {

            this.findNavController().navigate(TransformtestDirections.actionTransformtestToTitleFragment())

        }
        return binding.root
    }

}