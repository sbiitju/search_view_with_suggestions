package com.sbiitju.counterbutton

import MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.sbiitju.smart_search.SearchViewWithSuggestions

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var button: Button
    private lateinit var searchViewWithSuggestions: SearchViewWithSuggestions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        button = findViewById(R.id.searchBtn)
        searchViewWithSuggestions = findViewById(R.id.address)
        mainViewModel.searchResult.observe(this) {
            searchViewWithSuggestions.replaceSearchResult(it)
        }
        button.setOnClickListener {
            searchViewWithSuggestions.show()
        }
        searchViewWithSuggestions.searchViewAction =
            object : SearchViewWithSuggestions.SearchViewAction {
                override fun onReinitialize(editText: AppCompatEditText) {
                    mainViewModel.searchAddress("")
                }

                override fun onBackButtonClick(view: View) {
                    onBackPressed()
                }

                override fun onSearchQuery(query: String) {
                    mainViewModel.searchAddress(query)
                }

                override fun onResultItemClick(String: String) {
                    Toast.makeText(this@MainActivity, String, Toast.LENGTH_SHORT).show()
                }

            }
    }
}