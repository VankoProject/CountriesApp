package com.hfad.notesapp.countriesapp

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

fun languagesToString(languages: List<Language>) :String{
    return languages.joinToString{it.name}
}

fun formatNumber(number: Long): String{
    val string = NumberFormat.getInstance().format(number)
    return string
}

suspend fun loadSVG(imageView: ImageView, url:String){
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")){
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
    } else {
        imageView.load(url)
        {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
    }
}


