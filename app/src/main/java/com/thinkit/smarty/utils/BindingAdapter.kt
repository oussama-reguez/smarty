package com.thinkit.smarty.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

import com.thinkit.smarty.R
import java.text.SimpleDateFormat
import java.util.*




/**
 * added a new attribute "app:numberOfDevices" to textView to correctly format number of devices
 */
@BindingAdapter("app:numberOfDevices")
fun numberOfDevices(textView: TextView, deviceNumbers:Int) {
    val textDeviceNumber= if(deviceNumbers==1)  textView.context.getString(R.string.device) else textView.context.getString(R.string.devices)
    textView.text="$deviceNumbers $textDeviceNumber"

}




/**
 * added "url" attribute to Set an image to the imageView from a given url
 * added "bitmap" attribute to Set an image to the imageView from a given bitmap
 */
@BindingAdapter(value = ["imageName"], requireAll = false)
fun ImageView.setImageFromDb(imageName: String?) {
 imageName?.let {
     setImageResource(resources.getIdentifier(imageName, "drawable", context.packageName))
 }
}





