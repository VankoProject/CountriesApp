package com.hfad.notesapp.countriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hfad.notesapp.countriesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            with(binding) {
                val countryName =
                    binding.countryNameEditText.text.toString()  // get name of country from Edit text

                lifecycleScope.launch {
                    try {
                        val countries =
                            restCountriesApi.getCountryByName(countryName)  // make a request from service API and get a list of countries
                        val country = countries[0]


                        countryTextView.text = country.name
                        capitalNameTextView.text = country.capital
                        populationNameTextView.text = formatNumber(country.population)
                        squareNameTextView.text = formatNumber(country.area)
                        languageNameTextView.text = languagesToString(country.languages)

                        loadSVG(imageView, country.flag)

                        resultLayout.visibility = View.VISIBLE
                        statusLayout.visibility = View.INVISIBLE
                    } catch (e: Exception) {
                        searchTextView.text = "Страна не найдена"
                        imageSearch.setImageResource(R.drawable.ic_error)
                        resultLayout.visibility = View.INVISIBLE
                        statusLayout.visibility = View.VISIBLE
                    }
                }

            }
        }
    }
}