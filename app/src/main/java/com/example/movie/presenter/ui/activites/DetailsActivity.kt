package com.example.movie.presenter.ui.activites

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movie.R
import com.example.movie.databinding.ActivityDetailsBinding
import com.example.movie.domain.entity.Result
import com.example.movie.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint// AndroidEntryPoint  annotation to can use dagger hilt on this activity
class DetailsActivity : BaseActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_details)//Set the Activity's content view to the given layout and return the associated binding.


        binding.item= intent.extras?.getSerializable(Utils.MOVIEW_SELECTED) as Result?// Recive the movie from previous activity

    }
}