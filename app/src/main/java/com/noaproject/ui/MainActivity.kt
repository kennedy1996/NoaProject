package com.noaproject.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.noaproject.R
import com.noaproject.databinding.ActivityMainBinding
import com.noaproject.ui.recyclerView.ListNoaAdapter
import com.noaproject.ui.viewModel.NoaViewModel
import com.noaproject.ui.viewPager.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterPageView: ViewPagerAdapter
    private var adapterRecyclerView: RecyclerView.Adapter<ListNoaAdapter.ViewHolder>? = null

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider[NoaViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Noa Project"
        adapterPageView = ViewPagerAdapter(supportFragmentManager)
        viewModel.searchNoaData()
        checkingLiveData()
        onUpdateSwipe()
        showProgressBar()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.visibility = View.GONE
        }, 3000)
    }

    private fun checkingLiveData() {
        viewModel.getNoaData()?.observe(this, Observer { list ->
            if (list.isNotEmpty()) {
                adapterPageView.clearFragment()
                val titles = mutableListOf<String>()
                list.forEach { table -> titles.add(table.name) }
                titles.forEach { adapterPageView.addFragment(FirstFragment(), it) }
                config()
            } else {
                Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun onUpdateSwipe() {
        binding.swipe.setOnRefreshListener {
            viewModel.searchNoaData()
            checkDataAfterTime()
        }
    }

    private fun checkDataAfterTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.getNoaData().value?.size!! > 0) {
                adapterRecyclerView!!.notifyDataSetChanged()
            }
            binding.swipe.isRefreshing = false
        }, 3000)
    }

    private fun config() {
        binding.viewPager.adapter = adapterPageView
        binding.tbLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.currentItem = 1
        binding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                settingsRecyclerView(adapterPageView.getTitle(position))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        settingsRecyclerView(adapterPageView.getTitle(1))
    }

    private fun settingsRecyclerView(table: String) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapterRecyclerView = ListNoaAdapter(
            this,
            viewModel,
            table
        )
        binding.recyclerView.adapter = adapterRecyclerView
    }
}