package com.example.movie.presenter.ui.listener

import com.example.movie.domain.entity.Result
//Its a interface will called when movie clicked.
interface MovieListener {
fun onMoviewClick(item: Result)

}