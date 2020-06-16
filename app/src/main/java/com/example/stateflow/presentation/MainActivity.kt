package com.example.stateflow.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import com.example.stateflow.domain.iteractor.base.AsyncStatus
import com.example.stateflow.presentation.base.observe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userStateFlow.observe(lifecycleScope, Observer {
            when(it){
                AsyncStatus.Idle -> {
                    userResponse.text = ""
                }
                AsyncStatus.Loading -> {
                    userResponse.text = "Carregando..."
                }
                is AsyncStatus.Success -> {
                    userResponse.text = it.data.toString()
                }
                is AsyncStatus.Error -> {
                    userResponse.text = it.error.message
                }
            }
        })
    }

    private fun setupListeners(){
        btnGetUser.setOnClickListener {
            viewModel.getUser( userId.text.toString().toIntOrNull() ?: 0)
        }
    }
}


