package com.bts.flickr.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import butterknife.BindView
import com.bts.flickr.FlickrApp
import com.bts.flickr.R
import com.bts.flickr.config.ApiConfig
import com.bts.flickr.data.entity.Photo
import com.bts.flickr.data.resource.SuggestionProvider
import com.bts.flickr.di.search.SearchModule
import com.bts.flickr.ui.BaseActivity
import com.bts.flickr.ui.details.ImageDetailsActivity
import com.bts.flickr.ui.search.adapter.ImagesAdapter
import com.bts.flickr.ui.search.adapter.OnImageClickListener
import com.bts.flickr.utils.AndroidUtils
import javax.inject.Inject


class SearchActivity : BaseActivity(), SearchContract.View, OnImageClickListener, SearchView.OnQueryTextListener {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    lateinit var menuItem: MenuItem

    @Inject
    lateinit var presenter: SearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependency()
        setContentView(R.layout.activity_search)
        initPresenter()
        handleIntent(intent)
    }

    private fun initDependency() {
        val app = applicationContext as FlickrApp
        app.component!!.include(SearchModule()).inject(this)
    }

    private fun initPresenter() {
        presenter.bind(this)
        presenter.loadData(ApiConfig.METHOD_INTERESTINGNESS, getString(R.string.FLICKR_API_KEY))
    }

    override fun onSuccess(photoList: ArrayList<Photo>) {
        val adapter = ImagesAdapter(this)

        adapter.addItems(photoList)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, resources.getInteger(R.integer.columns_count))
        recyclerView.adapter = adapter
    }

    override fun onError(msg: String) {
        AndroidUtils.showShortToast(this, msg)
    }

    override fun onImageClick(view: ImageView, url: String) {
        ImageDetailsActivity.navigate(this, view, url)
    }

    override fun showLoadingIndicator() {
        showProgressBar()
    }

    override fun hideLoadingIndicator() {
        dismissProgressBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        menuItem = menu!!.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.searchImageByTag(getString(R.string.FLICKR_API_KEY), query!!)
        menuItem.collapseActionView()
        return false
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            val suggestions = SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE)
            suggestions.saveRecentQuery(query, null)
        }
    }
}
