package com.chirikualii.materidb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.chirikualii.materidb.databinding.ActivityMainBinding
import com.chirikualii.materidb.ui.adapter.MovieListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieListAdapter

    private val mViewModel: MainViewModel by viewModels(
        factoryProducer = { MainViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup adapter
        adapter = MovieListAdapter()
        binding.rvMovie.adapter = adapter

        mViewModel.doGetPopularMovie()
        observeView()

    }

    private fun observeView() {
        mViewModel.listMovie.observe(this) {
            adapter.addItem(it)

        }

        mViewModel.isLoading.observe(this){ isLoading ->
            if(isLoading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

    }
}

