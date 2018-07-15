package com.bts.flickr.ui.details

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import com.bts.flickr.R
import com.bts.flickr.ui.BaseActivity
import com.bts.flickr.utils.ImageUtils

class ImageDetailsActivity : BaseActivity() {

    @BindView(R.id.image)
    lateinit var image: ImageView

    companion object {
        private val IMAGE = "image"
        private val EXTRA_IMAGE = "imageExtra"

        fun navigate(activity: AppCompatActivity, transitionImage: View, url: String) {
            val intent = Intent(activity, ImageDetailsActivity::class.java)
            intent.putExtra(EXTRA_IMAGE, url)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE)
            ActivityCompat.startActivity(activity, intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimatedWindow()
        setContentView(R.layout.activity_details)
        initToolbarOptions()

        ViewCompat.setTransitionName(findViewById(R.id.parent_linear), IMAGE)

        val url = intent.getStringExtra(EXTRA_IMAGE)
        loadImage(url)
    }

    private fun initToolbarOptions() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setAnimatedWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = Slide()
            transition.excludeTarget(android.R.id.statusBarBackground, true)
            window.statusBarColor = Color.TRANSPARENT
            window.enterTransition = transition
            window.returnTransition = transition
        }
    }

    private fun loadImage(url: String) {
        ImageUtils.loadImage(image, url)
    }

}