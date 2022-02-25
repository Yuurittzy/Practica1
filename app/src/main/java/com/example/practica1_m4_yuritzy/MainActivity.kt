package com.example.practica1_m4_yuritzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.example.practica1_m4_yuritzy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                showText(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun goToResultActivity(view: View) {
        if (binding.etX.text.toString()=="")
        {
            Toast.makeText(this,"Por favor ingresa un número",Toast.LENGTH_SHORT).show()
        } else{
            startActivity(Intent(this, ResultActivity::class.java))
        }

    }

    fun showImage(position: Int) {
        val image = findViewById<ImageView>(R.id.ivFormula)
        val resource = when(position) {
            0 -> AppCompatResources.getDrawable(this, R.drawable.formula_gral)
            1 -> AppCompatResources.getDrawable(this, R.drawable.potencia)
            2 -> AppCompatResources.getDrawable(this, R.drawable.area_triangulo)

            else -> AppCompatResources.getDrawable(this, R.drawable.formula_gral)
        }
        image.setImageDrawable(resource)
    }

    fun showText(position: Int) {
        val tvx= findViewById<TextView>(R.id.tvX)
        val tvy= findViewById<TextView>(R.id.tvY)
        val tvz= findViewById<TextView>(R.id.tvZ)
        val et= findViewById<EditText>(R.id.etZ)

        if (position==1){
            tvz.visibility = View.INVISIBLE
            et.visibility = View.INVISIBLE
            tvx.text = "W"
            tvy.text = "t"
        }else if(position==2){
            tvz.visibility = View.INVISIBLE
            et.visibility = View.INVISIBLE
            tvx.text = "b"
            tvy.text = "h"
        }
        else{
            tvz.visibility = View.VISIBLE
            tvx.text = "a"
            tvy.text = "b"
            tvz.text = "c"
        }

    }
}