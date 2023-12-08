package com.example.kompgraph2

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class First2 : AppCompatActivity() {
    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first1)
        val imageView: ImageView = findViewById(R.id.icon)
        val seekbar: SeekBar = findViewById<SeekBar>(R.id.seekBar)
        seekbar.progressDrawable = resources
            .getDrawable(R.xml.progress_bar)
        Glide.with(this)
            .asBitmap()
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5QR3KDOSXjr2Q7PGtBY04BM2cxSdnpyljMA&usqp=CAU")
            .centerInside()
            .apply(RequestOptions().override(8000, 4000))
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                            imageView.setImageBitmap(adjustedContrast(resource, progress.toDouble()/10))

                        }
                        override fun onStartTrackingTouch(seekBar: SeekBar) {}
                        override fun onStopTrackingTouch(seekBar: SeekBar) {}
                    })
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun adjustedContrast(src: Bitmap, value: Double): Bitmap? {
        val width = src.width
        val height = src.height

        val bmOut = Bitmap.createBitmap(width, height, src.config)

        val c = Canvas()
        c.setBitmap(bmOut)

        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        val contrast = Math.pow((100 + value) / 100, 2.0)

        for (x in 0 until width) {
            for (y in 0 until height) {
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                R = (((R / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (R < 0) {
                    R = 0
                } else if (R > 255) {
                    R = 255
                }
                G = Color.green(pixel)
                G = (((G / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (G < 0) {
                    G = 0
                } else if (G > 255) {
                    G = 255
                }
                B = Color.blue(pixel)
                B = (((B / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (B < 0) {
                    B = 0
                } else if (B > 255) {
                    B = 255
                }
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }
        return bmOut
    }
}

