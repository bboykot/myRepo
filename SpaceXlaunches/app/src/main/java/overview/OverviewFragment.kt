package overview

import android.example.com.spacex_launches.databinding.FragmentOverviewBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import db.SpaceDatabase


class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner=this

        //block of creating viewModel and transfering parameters to him
        val application = requireNotNull(this.activity).application
        val dataSource = SpaceDatabase.getInstance(application).spaceDao
        val viewModelFactory = OverviewViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel

        //recycler + moving to OverviewFragment
        binding.photosGrid.adapter= SpaceXGridAdapter(SpaceXGridAdapter.OnClickListener { Models ->
           this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(Models))
        })


        //display network error
        viewModel.networkResponse.observe(viewLifecycleOwner, Observer {response->
            if (viewModel.networkResponse.value != "ok") {
                Toast.makeText(activity, "Network error", Toast.LENGTH_LONG).show()
            }
        })
        return binding.root
    }

}