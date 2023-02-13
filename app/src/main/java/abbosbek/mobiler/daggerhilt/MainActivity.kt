package abbosbek.mobiler.daggerhilt

import abbosbek.mobiler.daggerhilt.adapter.ImageAdapter
import abbosbek.mobiler.daggerhilt.databinding.ActivityMainBinding
import abbosbek.mobiler.daggerhilt.viewModel.ImageViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var imageAdapter : ImageAdapter

    private val viewModel : ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = with(binding) {
        recyclerView.apply {
            imageAdapter = ImageAdapter()
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        viewModel.responseImage.observe(this@MainActivity){response->
            if (response != null){
                imageAdapter.submitList(response)
            }
        }
    }
}