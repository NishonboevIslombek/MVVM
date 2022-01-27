package com.android.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvm.databinding.ActivityMainBinding
import com.android.mvvm.ui.adapter.MainRvAdapter
import com.android.mvvm.ui.viewModel.LatestUsers
import com.android.mvvm.ui.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        //this is how to get response with StateFlow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                model.uiState.collect {
                    when(it){
                        is LatestUsers.Success -> rvAdapter.updateItemList(it.users)
                        is LatestUsers.Error -> Toast.makeText(applicationContext,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}