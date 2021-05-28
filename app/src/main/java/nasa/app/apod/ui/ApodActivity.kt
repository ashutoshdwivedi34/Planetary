package nasa.app.apod.ui

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.CollapsingToolbarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_apod.*
import kotlinx.android.synthetic.main.content_scrolling.*
import nasa.app.apod.R
import nasa.app.apod.data.entities.ApodData
import nasa.app.apod.utils.Resource



@AndroidEntryPoint
class ApodActivity : AppCompatActivity() {

    private  val  apodViewModel: APODViewModel by viewModels()
    var api_key = "uKNPca0QdNNxvj78kqKbWg14TSVfXk8ddepMFSmF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        apodViewModel.start(api_key)
        setupObservers()

    }

    private fun setupObservers() {
        apodViewModel.apodData.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let {
                        setDataToUi(it)
                        apod_pg_loading.visibility = View.GONE

                    }

                }

                Resource.Status.ERROR ->{
                    apod_pg_loading.visibility = View.GONE
                    apod_tv_author.text = it.message
                }



                Resource.Status.LOADING -> {
                    apod_pg_loading.visibility = View.VISIBLE

                }
            }
        })

        apodViewModel.networkConnected.observe(this, Observer {
            if (!it){
                Toast.makeText(this,getString(R.string.network_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setDataToUi(data: ApodData) {

        Glide.with(this).load(data.hdurl)
            .into(object : SimpleTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    toolbar_layout.background = resource
                }

            })

        apod_tv_title.text = data.title
        apod_tv_explanation.text = data.explanation
        apod_tv_date.text = getString(R.string.label_date,data.date)
        apod_tv_author.text = getString(R.string.label_author,data.copyright)


    }
}