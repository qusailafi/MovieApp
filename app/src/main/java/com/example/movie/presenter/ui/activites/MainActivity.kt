package com.example.movie.presenter.ui.activites
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
 import com.example.movie.presenter.ui.adapters.AdapterMovies
import com.example.movie.presenter.viewmodels.MovieViewModel
import com.example.movie.R
import com.example.movie.utils.Status
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.domain.entity.Result
import com.example.movie.presenter.ui.listener.MovieListener
import com.example.movie.utils.PaginationScrollListener
import com.example.movie.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint// AndroidEntryPoint  annotation to can use dagger hilt on this activity
class MainActivity : AppCompatActivity(), MovieListener {
    val viewModel: MovieViewModel by viewModels()//Make instance from MovieViewModel
    lateinit var popularityAdapter: AdapterMovies
    lateinit var revenueAdapter: AdapterMovies
    lateinit var topRatedAdapter: AdapterMovies
    lateinit var binding: ActivityMainBinding
    var populatePageSize: Int = 0
    var topRatedPageSize: Int = 0
    var revenuePageSize: Int = 0
    var topRatedPageNumber: Int = 1
    var revenuePageNumber: Int = 1
    var popUlatePageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )//Set the Activity's content view to the given layout and return the associated binding.
        initPopularityRv()
        initRevenueRv()
        initTopRatedRv()
        getPopularity()
        getRevenue()
        getTopRated()
        topRatedScroll()
        popUlarityScrollListener()
        reveneuScroll()
    }

    fun getPopularity() {
        //Pull movie list based on popularity fillter
        lifecycleScope.launch {
            viewModel.getPopularity(popUlatePageNumber)
                .collect {//Collect its afunction used to observe on Viewmodel and update the ui.
                    when (it.status) {
                        Status.NONE -> {
                        }
                        Status.ERROR -> {
                            viewModel.setPopUlateLoading(false)
                        }
                        Status.SUCCESS -> {
                            popUlatePageNumber++
                            populatePageSize = it.data!!.total_pages
                            popularityAdapter.updateAdapter(it.data!!.results as ArrayList<Result>)
                            viewModel.setPopUlateLoading(false)
                        }
                        Status.FAILURE -> {
                            viewModel.setPopUlateLoading(false)
                        }
                        Status.LOADING -> {
                            viewModel.setPopUlateLoading(true)
                        }
                        Status.NETWORK_ERROR -> {
                            viewModel.setPopUlateLoading(false)
                        }
                    }
                }
        }

    }

    fun getTopRated() {
        //Pull movie list based on Rating fillter
        lifecycleScope.launch {
            viewModel.getTopRted(topRatedPageNumber)
                .collect {//Collect its a function   used to observe on Viewmodel and update the ui.
                    when (it.status) {
                        Status.NONE -> {
                            viewModel.setTopRatedLoading(false)
                        }
                        Status.ERROR -> {
                            viewModel.setTopRatedLoading(false)
                        }
                        Status.SUCCESS -> {
                            topRatedAdapter.updateAdapter(it.data!!.results as ArrayList<Result>)
                            topRatedPageSize = it.data!!.total_pages
                            topRatedPageNumber++

                            viewModel.setTopRatedLoading(false)
                        }
                        Status.FAILURE -> {
                            viewModel.setTopRatedLoading(false)
                        }
                        Status.LOADING -> {
                            viewModel.setTopRatedLoading(true)
                        }
                        Status.NETWORK_ERROR -> {
                            viewModel.setTopRatedLoading(false)
                        }
                    }
                }
        }

    }

    fun getRevenue() {
        //Pull movie list based on Revenue fillter
        lifecycleScope.launch {
            viewModel.getRevenue(revenuePageNumber)
                .collect {//Collect its a function   used to observe on Viewmodel and update the ui.
                    when (it.status) {
                        Status.NONE -> {

                        }
                        Status.ERROR -> {
                            viewModel.setRevenue(false)

                        }
                        Status.SUCCESS -> {
                            revenueAdapter.updateAdapter(it.data!!.results as ArrayList<Result>)
                            revenuePageSize = it.data!!.total_pages
                            revenuePageNumber++
                            viewModel.setRevenue(false)
                        }
                        Status.FAILURE -> {
                            viewModel.setRevenue(false)
                        }
                        Status.LOADING -> {
                            viewModel.setRevenue(true)
                        }
                        Status.NETWORK_ERROR -> {
                            viewModel.setRevenue(false)

                        }
                    }
                }
        }

    }

    fun initPopularityRv() {
        //Make Popularity recycler view settings and Initalize  AdapterMovies.
        var manger: LinearLayoutManager = LinearLayoutManager(this)
        manger.orientation = LinearLayoutManager.HORIZONTAL
        popularityAdapter = AdapterMovies(ArrayList<Result>(), this)
        binding.popularityRv.layoutManager = manger
        binding.popularityRv.adapter = popularityAdapter
    }

    fun initRevenueRv() {
        //Make Revenue recycler view settings and Initalize  AdapterMovies.
        var manger: LinearLayoutManager = LinearLayoutManager(this)
        manger.orientation = LinearLayoutManager.HORIZONTAL
        revenueAdapter = AdapterMovies(ArrayList<Result>(), this)
        binding.revenueRv.layoutManager = manger
        binding.revenueRv.adapter = revenueAdapter
    }

    private fun popUlarityScrollListener() {
        //Add scroll listener to Popularity RecyclerView
        binding.popularityRv.addOnScrollListener(object :
            PaginationScrollListener(binding.popularityRv.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                getPopularity()
            }

            override fun isLastPage() = viewModel.isLastPage(populatePageSize, popUlatePageNumber)
            override fun isLoading(): Boolean = viewModel.isPopulateLoad()

        })
    }

    private fun topRatedScroll() {
        //Add scroll listener to TopRated RecyclerView
        binding.topeRatedRv.addOnScrollListener(object :
            PaginationScrollListener(binding.topeRatedRv.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                getTopRated()
            }

            override fun isLastPage() = viewModel.isLastPage(topRatedPageSize, topRatedPageNumber)
            override fun isLoading(): Boolean = viewModel.isTopRatedLoad()
        })
    }

    private fun reveneuScroll() {
        //Add scroll listener to Revenue RecyclerView
        binding.revenueRv.addOnScrollListener(object :
            PaginationScrollListener(binding.revenueRv.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                getRevenue()
            }

            override fun isLastPage() = viewModel.isLastPage(revenuePageSize, revenuePageNumber)
            override fun isLoading(): Boolean = viewModel.isRevenueLoad()
        })
    }

    fun initTopRatedRv() {
        //Make TopRated recycler view settings and Initalize  AdapterMovies.
        var manger: LinearLayoutManager = LinearLayoutManager(this)
        manger.orientation = LinearLayoutManager.HORIZONTAL
        topRatedAdapter = AdapterMovies(ArrayList<Result>(), this)
        binding.topeRatedRv.layoutManager = manger
        binding.topeRatedRv.adapter = topRatedAdapter
    }

    override fun onMoviewClick(item: Result) {
        var bundel = Bundle()
        bundel.putSerializable(Utils.MOVIEW_SELECTED, item)
         Utils.goToActivity(this,DetailsActivity::class.java,bundel)

    }

}