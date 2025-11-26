package com.whatahotel.rates

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel: HotelRatesViewModel by viewModels()
    private lateinit var adapter: HotelRatesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var lastUpdateText: TextView
    private lateinit var emptyView: View
    private lateinit var refreshFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        lastUpdateText = findViewById(R.id.lastUpdateText)
        emptyView = findViewById(R.id.emptyView)
        refreshFab = findViewById(R.id.refreshFab)

        swipeRefresh.setOnRefreshListener {
            viewModel.refreshRates()
        }

        refreshFab.setOnClickListener {
            viewModel.refreshRates()
        }
    }

    private fun setupRecyclerView() {
        adapter = HotelRatesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.hotelRates.observe(this) { rates ->
            adapter.submitList(rates)
            emptyView.visibility = if (rates.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(this) { isLoading ->
            swipeRefresh.isRefreshing = isLoading
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.lastUpdateTime.observe(this) { time ->
            lastUpdateText.text = time
        }
    }
}
