package com.info.mediapicker

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.info.mediapicker.databinding.ActivityMainBinding
import com.robertlevonyan.components.picker.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        setViews()


    }

    private fun setViews() {
        binding.apply {

            /** button Media **/
            btnMedia.setOnClickListener {
                selectFromMedia()
            }


        }
    }

    private fun selectFromMedia() {
        val items = setOf(
            ItemModel(
                ItemType.Camera,
                backgroundType = ShapeType.TYPE_ROUNDED_SQUARE,
                itemBackgroundColor = Color.RED),
            ItemModel(ItemType.Video),
            ItemModel(ItemType.ImageGallery()),
            ItemModel(ItemType.AudioGallery()),
            ItemModel(ItemType.VideoGallery()),
            ItemModel(ItemType.Files())
        )

        // in fragment or activity
        pickerDialog {
            setTitle("select media")          // String value or resource ID
            setTitleTextSize(20F)  // Text size of title
            setTitleTextColor(R.color.black) // Color of title text
            setListType(PickerDialog.ListType.TYPE_LIST)       // Type of the picker, must be PickerDialog.TYPE_LIST or PickerDialog.TYPE_Grid
            setItems(items)          // List of ItemModel-s which should be in picker
        }.setPickerCloseListener { type: ItemType, uris: List<Uri> ->
            // Getting the result
            when (type) {
                ItemType.Camera -> { showToast("Camera ${uris.size}") } // photo you've taken.
                ItemType.Video ->{ showToast("Video") } // video you've recorded.
                is ItemType.ImageGallery ->{ showToast("Image Gallery ${uris.size}") } /* images you've chosen */
                is ItemType.AudioGallery -> { showToast("Audio Gallery ${uris.size}") }/* audios you've chosen */
                is ItemType.VideoGallery -> { showToast("Video Gallery ${uris.size}") }/*  videos you've chosen */
                is ItemType.Files -> { showToast("Files ${uris.size}") } /* the files you've chosen */
            }
        }.show()

    }

    private fun showToast(t: String) { Toast.makeText(this,t,Toast.LENGTH_SHORT).show() }

}