package com.example.patemperatureconversion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

// Patrick Dave B. Pancho
// PA - Temperature Conversion App
// Date: November 21, 2026
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val input = findViewById<EditText>(R.id.temperatureInput)
        val options = findViewById<RadioGroup>(R.id.conversionGroup)
        val result = findViewById<TextView>(R.id.conversionResult)
        val convertButton = findViewById<Button>(R.id.convertButton)

        convertButton.setOnClickListener {
            result.text = ""
            val temperature = input.text.toString().trim().toDoubleOrNull()

            if (temperature == null || !temperature.isFinite()) {
                showMessage("Enter a valid temperature.")
                return@setOnClickListener
            }

            val fahrenheitToCelsius =
                options.checkedRadioButtonId == R.id.fahrenheitToCelsius

            if (fahrenheitToCelsius) {
                if (temperature !in -100.0..250.0) {
                    showMessage("Enter a temperature from -100 °F to 250 °F.")
                    return@setOnClickListener
                }

                val celsius = (temperature - 32.0) * 5.0 / 9.0
                result.text = String.format(Locale.US, "%.1f °C", celsius)
            } else {
                if (temperature !in -75.0..125.0) {
                    showMessage("Enter a temperature from -75 °C to 125 °C.")
                    return@setOnClickListener
                }

                val fahrenheit = temperature * 9.0 / 5.0 + 32.0
                result.text = String.format(Locale.US, "%.1f °F", fahrenheit)
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}