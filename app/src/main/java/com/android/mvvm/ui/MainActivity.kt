package com.android.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvm.databinding.ActivityMainBinding
import com.android.mvvm.ui.adapter.MainRvAdapter
import com.android.mvvm.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModel() // this is how intialize viewModel in Activity with Koin

    private lateinit var binding: ActivityMainBinding
    lateinit var rvAdapter: MainRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        rvAdapter = MainRvAdapter()
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = rvAdapter;
        getUsers()
    }

    private fun getUsers() {
        //this is how to get response with LiveData
        model.getUsers().observe(this, { users ->
            rvAdapter.updateItemList(users)
        })

    }
}