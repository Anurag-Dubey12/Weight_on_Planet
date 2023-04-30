package com.example.weight

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    val planets= intArrayOf(R.drawable.mercury,R.drawable.venus,R.drawable.mars,R.drawable.jupiter,R.drawable.sature,R.drawable.uranus,R.drawable.neptune,R.drawable.pluto)
    var index=0
//    val planetname= arrayOf("Mercury","Venus","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto")
val planetsdescription = arrayOf(
    Planet(
        "Mercury",
        3.285e23,
        3.7,
        listOf("Iron", "Nickel")
    ),
    Planet(
        "Venus",
        4.867e24,
        8.87,
        listOf("Sulfuric Acid", "Metals")
    ),
    Planet(
        "Mars",
        6.39e23,
        3.711,
        listOf("Iron", "Aluminum")
    ),
    Planet(
        "Jupiter",
        1.898e27,
        24.79,
        listOf("Hydrogen", "Helium")
    ),
    Planet(
        "Saturn",
        5.683e26,
        10.44,
        listOf("Hydrogen", "Helium")
    ),
    Planet(
        "Uranus",
        8.681e25,
        8.69,
        listOf("Ice", "Rock")
    ),
    Planet(
        "Neptune",
        1.024e26,
        11.15,
        listOf("Ice", "Rock")
    ),
    Planet(
        "Pluto",
        1.309e22,
        0.62,
        listOf("Nitrogen", "Methane", "Carbon Monoxide")
    )
)
    var WeightValue= mutableListOf<Double>()
    private lateinit var submit:Button
    private lateinit var weighttext:EditText
    private lateinit var imageSwitcher: ImageSwitcher
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton
    private lateinit var planetNames: TextView
    private lateinit var planetDes: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSwitcher = findViewById(R.id.imageSwitcher)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)
        planetNames = findViewById(R.id.PlanetNames)
        planetDes = findViewById(R.id.PlanetDes)
        submit=findViewById(R.id.submit_button)
        weighttext=findViewById(R.id.WeightText)


        imageSwitcher.setFactory({
            val imge=ImageView(applicationContext)
            imge.scaleType=ImageView.ScaleType.FIT_CENTER
            imge.setPadding(8,8,8,8)
            imge
        })

        imageSwitcher.setImageResource(planets[index])
        displayPlanetInfo(index)


        btnRight.setOnClickListener {
            index=if(index+1<planets.size)index+1 else 0
            imageSwitcher.setImageResource(planets[index])
            planetNames.text= "Your weight on these planet is:"+WeightValue[index].toString()
            displayPlanetInfo(index)
        }
        btnLeft.setOnClickListener {
            index=if(index-1>=0)index-1 else 2
            imageSwitcher?.setImageResource(planets[index])
            planetNames.text="Your weight on these planet is:"+WeightValue[index].toString()
            displayPlanetInfo(index)
        }

        submit.setOnClickListener{
            val weightcal = weighttext.text.toString().toDoubleOrNull()
            when{
                weightcal==null->{
                    Toast.makeText(this,"Please Enter your Weight",Toast.LENGTH_SHORT).show()
                }
                else->{
                    var massonmercury = weightcal!! * 0.38
                    WeightValue.add(massonmercury)
                    var massonvenus = weightcal!! * 0.91
                    WeightValue.add(massonvenus)
                    var massonmars = weightcal!! * 0.38
                    WeightValue.add(massonmars)
                    val massOnJupiter = weightcal * 2.34
                    WeightValue.add(massOnJupiter)
                    val massOnSaturn = weightcal * 1.06
                    WeightValue.add(massOnSaturn)
                    val massOnUranus = weightcal * 0.92
                    WeightValue.add(massOnUranus)
                    val massOnNeptune = weightcal * 1.19
                    WeightValue.add(massOnNeptune)
                    val massOnPluto = weightcal * 0.06
                    WeightValue.add(massOnPluto)
                    planetNames.text= "Your weight on these planet is:"+WeightValue[index].toString()
                }
            }
            WeightValue.clear()

        }


    }

    private fun displayPlanetInfo(index: Int) {
        val planet = planetsdescription[index]
       planetDes.text=planet.name+"\n Mass :${planet.mass}kg\n Gravity of a Planet is ${planet.gravity}m/s²\n Resource Avaliable there ${planet.resources}"
    }
}