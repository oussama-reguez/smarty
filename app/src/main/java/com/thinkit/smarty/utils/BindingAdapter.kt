package com.thinkit.smarty.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.thinkit.smarty.R


/**
 * added a new attribute "app:numberOfDevices" to textView to correctly format number of devices
 */
@BindingAdapter("numberOfDevices")
fun numberOfDevices(textView: TextView, deviceNumbers: Int) {
    val textDeviceNumber =
        if (deviceNumbers == 1) textView.context.getString(R.string.device) else textView.context.getString(
            R.string.devices
        )
    textView.text = "$deviceNumbers $textDeviceNumber"

}


/**
 * added "imageName" attribute to Set an image by resource name
 */
@BindingAdapter(value = ["imageName"], requireAll = false)
fun ImageView.setImageFromDb(imageName: String?) {
    imageName?.let {
        setImageResource(resources.getIdentifier(imageName, "drawable", context.packageName))
    }
}





