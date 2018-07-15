package com.bts.flickr.ui

import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bts.flickr.R
import kotlinx.android.synthetic.main.include_toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    @Nullable
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    private lateinit var unbinder: Unbinder

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        unbinder = ButterKnife.bind(this)

        initToolbar()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    protected fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    protected fun dismissProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener { _ -> onBackPressed() }
    }
}