package com.example.dado

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtNumber: TextView = findViewById(R.id.txtNumber)
        val mainActivity: LinearLayout = findViewById(R.id.main)
        val imgDice: ImageView = findViewById(R.id.imgDice)

        mainActivity.setOnClickListener {
            // Mostrar el GIF de animación
            Glide.with(this).load(R.drawable.dice) // Reemplaza con el nombre de tu archivo GIF
                .into(imgDice) // Aquí se carga el GIF en el ImageView

            // Simular el tiempo de la animación (por ejemplo, 1 segundo)
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val randomNumber = (1..6).random()

                    val numberText = when (randomNumber) {
                        1 -> "uno"
                        2 -> "dos"
                        3 -> "tres"
                        4 -> "cuatro"
                        5 -> "cinco"
                        6 -> "seis"
                        else -> "desconocido"  // Este caso no debería ocurrir nunca
                    }

                    // Actualiza el texto del TextView
                    txtNumber.text = "Número aleatorio: $numberText"

                    // Actualiza la imagen del dado en el ImageView
                    val drawableResource = resources.getIdentifier(
                        numberText, "drawable", packageName
                                                                  )
                    imgDice.setImageResource(drawableResource) // Aquí se cambia la imagen por el dado

                    // Reproducir el archivo de audio correspondiente
                    val audioResourceId = resources.getIdentifier(
                        numberText + "_voz", "raw", packageName
                                                                 )

                    // Usar MediaPlayer para reproducir el audio
                    if (audioResourceId != 0) {
                        val mediaPlayer = MediaPlayer.create(this, audioResourceId)
                        mediaPlayer.start()
                    }
                }, 1500 // Ajusta el tiempo de animación según lo que necesites
                                                       )
        }
    }
}
