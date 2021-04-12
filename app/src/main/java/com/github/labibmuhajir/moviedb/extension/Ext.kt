package com.github.labibmuhajir.moviedb.extension

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import coil.load
import com.github.labibmuhajir.moviedb.di.AppModule

enum class ImageSize(val size: String) {
    w500("w500"),
    w200("w200")
}

fun ImageView.loadUrl(url: String, size: ImageSize = ImageSize.w200) {
    val imageUrl = "${AppModule.imageUrl}/${size.size}/$url"
    load(imageUrl)
}

fun FragmentManager.addOrReplace(@IdRes container: Int, fragment: Fragment) {
    if (fragments.isEmpty()) {
        beginTransaction().add(container, fragment).commit()
    } else {
        beginTransaction().replace(container, fragment).commit()
    }
}

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }