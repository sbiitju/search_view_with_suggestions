package com.sbiitju.smart_search

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView

class SearchViewWithSuggestions @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var layoutParent: FrameLayout
    private var btnBack: AppCompatImageView
    private var btnClear: AppCompatImageView
    private var edtSearch: AppCompatEditText
    private var rvSearchResult: RecyclerView

    var searchViewAction: SearchViewAction? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_search, this, true)
        layoutParent = findViewById(R.id.layoutParentSearch)
        btnBack = findViewById(R.id.btnBackSearch)
        btnClear = findViewById(R.id.ivClearSearch)
        edtSearch = findViewById(R.id.edtSearch)
        rvSearchResult = findViewById(R.id.rvSearch)

        btnBack.setOnClickListener {
            searchViewAction?.onBackButtonClick(it)
        }
        btnClear.setOnClickListener {
            edtSearch.setText("")
        }
        rvSearchResult.adapter = SearchResultAdapter {
            searchViewAction?.onResultItemClick(it)
        }
        edtSearch.doAfterTextChanged {
            val query = it?.toString() ?: ""
            searchViewAction?.onSearchQuery(query)
            if (query.isEmpty()) {
                replaceSearchResult(null)
                btnClear.visibility = View.GONE
            } else {
                btnClear.visibility = View.VISIBLE
            }
        }
    }

    fun replaceSearchResult(result: List<String>?) {
        (rvSearchResult.adapter as? SearchResultAdapter)?.replaceData(result)
        if (result.isNullOrEmpty()) {
            layoutParent.background = null
        } else {
            layoutParent.background = ContextCompat.getDrawable(context, R.color.colorPrimary)
        }
    }

    fun show() {
        visibility = View.VISIBLE
        edtSearch.setText("")
        searchViewAction?.onReinitialize(edtSearch)
    }

    fun hide() {
        visibility = View.GONE
    }

    interface SearchViewAction {
        fun onReinitialize(editText: AppCompatEditText)
        fun onBackButtonClick(view: View)
        fun onSearchQuery(query: String)
        fun onResultItemClick(String: String)
    }

    override fun dispatchKeyEventPreIme(event: KeyEvent?): Boolean {
        return (rvSearchResult.adapter as? SearchResultAdapter).let { adapter ->
            if (adapter?.dataSet?.isEmpty() == true) {
                searchViewAction?.onBackButtonClick(this)
                true
            } else {
                super.dispatchKeyEventPreIme(event)
            }
        }
    }

}
