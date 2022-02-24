package com.example.practica1_m4_yuritzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpinnerData()
    }

    private fun setSpinnerData() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val values = listOf(getString(R.string.spinner_op1),getString(R.string.spinner_op2),getString(R.string.spinner_op3))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, values)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showImage(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun goToResultActivity(view: View) {
        startActivity(Intent(this, ResultActivity::class.java))
    }

    fun showImage(position: Int) {
        val image = findViewById<ImageView>(R.id.imageView3)
        val resource = when(position) {
            0 -> AppCompatResources.getDrawable(this, R.drawable.formula_gral)
            1 -> AppCompatResources.getDrawable(this, R.drawable.potencia)
            2 -> AppCompatResources.getDrawable(this, R.drawable.area_triangulo)

            else -> AppCompatResources.getDrawable(this, R.drawable.formula_gral)
        }


        image.setImageDrawable(resource)
    }

}