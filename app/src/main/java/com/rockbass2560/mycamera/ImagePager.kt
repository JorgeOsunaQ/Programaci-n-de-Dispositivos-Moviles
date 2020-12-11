package com.rockbass2560.mycamera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import java.io.File
import java.io.FilenameFilter


class ImagePager : AppCompatActivity() {

    lateinit var actualFile: File;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_pager)

        val folderImage = File(filesDir, "image")
        val files = folderImage.listFiles(FilenameFilter { dir, name ->
            name.contains("image_")
        })?.reversed()

        val viewPager = findViewById<ViewPager2>(R.id.viewpager)

        val photoPagerAdapter = PhotoPagerAdapter(this, files!!)

        viewPager.adapter = photoPagerAdapter

        actualFile= files[viewPager.currentItem];

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                actualFile = files[viewPager.currentItem];
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }

    private inner class PhotoPagerAdapter(fa: FragmentActivity, private val files: List<File>) :
        FragmentStateAdapter(fa) {
        val cacheFragments = mutableMapOf<Int, Fragment>()

        override fun getItemCount(): Int = files.size

        override fun createFragment(position: Int): Fragment {
            val imagePageFragment: Fragment
            if (!cacheFragments.containsKey(position)) {
                val file = files[position]
                imagePageFragment = ImagePageFragment(file)
                cacheFragments[position] = imagePageFragment
            } else {
                imagePageFragment = cacheFragments[position]!!
            }

            return imagePageFragment
        }
    }

    fun shareImage(view: View) {

        Log.d("PROVIDER",this.getApplicationContext().getPackageName().toString() )

        val imageURI: Uri = FileProvider.getUriForFile(
            this,
            this.getApplicationContext().getPackageName().toString() + ".provider",
            this.actualFile
        )

        intent= Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, imageURI)
        intent.type="image/jpge"
        startActivity(intent)
    }
}