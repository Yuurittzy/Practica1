package com.example.practica1_m4_yuritzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.example.practica1_m4_yuritzy.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var positionTmp =0

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
                positionTmp=position

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun goToResultActivity(view: View) {
        if (binding.etX.text.toString()=="")
        {
            Toast.makeText(this,getString(R.string.ingresar_num),Toast.LENGTH_SHORT).show()
            binding.etX.error=getString(R.string.campo)
        } else if(binding.etY.text.toString()=="") {
            Toast.makeText(this,getString(R.string.ingresar_num),Toast.LENGTH_SHORT).show()
            binding.etY.error=getString(R.string.campo)
        } else if ( binding.etZ.visibility == View.VISIBLE && binding.etZ.text.toString()=="") {
            Toast.makeText(this,getString(R.string.ingresar_num),Toast.LENGTH_SHORT).show()
            binding.etZ.error=getString(R.string.campo)
        }
        else{
            showResult(positionTmp)
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
        val etZ= findViewById<EditText>(R.id.etZ)

        when (position) {
            1 -> {
                tvz.visibility = View.INVISIBLE
                etZ.visibility = View.INVISIBLE
                tvx.text = getString(R.string.w)
                tvy.text = getString(R.string.t)
            }
            2 -> {
                tvz.visibility = View.INVISIBLE
                etZ.visibility = View.INVISIBLE
                tvx.text = getString(R.string.b2)
                tvy.text = getString(R.string.h)
            }
            else -> {
                tvz.visibility = View.VISIBLE
                tvx.text = getString(R.string.a)
                tvy.text = getString(R.string.b)
                tvz.text = getString(R.string.c)
            }
        }

    }

    private fun calculateGeneralFormula(): String{
            val a = binding.etX.text.toString().toDouble()
            val b = binding.etY.text.toString().toDouble()
            val c = binding.etZ.text.toString().toDouble()

            val resultPositive = ((-1 * b) + sqrt(((b*b) -(4 * a * c)))) / (2*a)
            val resultNegative = ((-1 * b) - sqrt(((b*b) -(4 * a * c)))) / (2*a)

        return ("x1= $resultPositive\nx2 = $resultNegative")
    }

    private fun calculatePotency(): String{
        val w = binding.etX.text.toString().toDouble()
        val t = binding.etY.text.toString().toDouble()

        return ("P= " + (w/t).toString())
    }

    private fun calculateAreaTriangle(): String{
        val b = binding.etX.text.toString().toDouble()
        val h = binding.etY.text.toString().toDouble()

        return ("A= " + ((b*h)/2).toString())
    }

    private fun showResult(position: Int) {
        val intent = Intent(this, ResultActivity::class.java)
        val bundle = Bundle()
        val result  = when (position) {
            0 -> calculateGeneralFormula()
            1 -> calculatePotency()
            2  -> calculateAreaTriangle()
            else -> ""
        }
        bundle.putString("RESULT", result)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}