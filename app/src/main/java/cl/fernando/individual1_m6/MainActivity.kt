package cl.fernando.individual1_m6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.fernando.individual1_m6.databinding.ActivityMainBinding

/*
    [ ] Navigation
    [ ] Fragmento agregar
    [ ] Crear Interfaz
    [ ] Room
        [ ] _Entity
        [ ] _DAO
        [ ] _Base de datos
    [ ] Fragmento listado
    [ ] ReccyclerView

 */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}