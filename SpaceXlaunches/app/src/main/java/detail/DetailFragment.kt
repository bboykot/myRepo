package detail

import android.content.Intent
import android.example.com.spacex_launches.R
import android.example.com.spacex_launches.databinding.FragmentDetailBinding
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner=this

        //getting data from overviewFragment
        val models = DetailFragmentArgs.fromBundle(arguments).models

        //creating viewmodel
        val application = requireNotNull(activity).application
        val viewModelfactory = DetailViewModelfactory(application, models)
        viewModel = ViewModelProvider(this, viewModelfactory).get(DetailViewModel::class.java)

        //connect xml to viewmodel class
        binding.viewModel=viewModel

        //open youtube by pressing image - using onclick - demonstration livedata observe
        viewModel.openYouTuBe.observe(viewLifecycleOwner, Observer { openYouTube->
            if (openYouTube){

                if (viewModel.models.value?.modelsLinks?.videoLink != null ) {

                    var intent = Intent(Intent.ACTION_VIEW)
                    intent.setPackage("com.google.android.youtube")
                    intent.setData(Uri.parse("${viewModel.models.value?.modelsLinks?.videoLink}"))
                    startActivity(intent)
                    viewModel.videoOpenned()
                }
                else Toast.makeText(context, R.string.no_video, Toast.LENGTH_LONG).show()
            }
        })


        //open article in browser
        binding.openArticleBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${viewModel.models.value?.modelsLinks?.articleLink}"))
            startActivity(browserIntent)
        }

        //open wiki in browser
        binding.openWikiBtn.setOnClickListener {
            val browserIntent2 = Intent(Intent.ACTION_VIEW, Uri.parse("${viewModel.models.value?.modelsLinks?.wikipediaLink}"))
            startActivity(browserIntent2)
        }

        return binding.root
    }
}