package cl.fernando.individual1_m6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cl.fernando.individual1_m6.databinding.FragmentAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class AddFragment: Fragment() {
    lateinit var  binding : FragmentAddBinding
    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        initRepositorio()
        initListener()
        loadTasks()
        return binding.root
    }

    private fun initRepositorio(){
        repositorio = Repositorio(TareaBaseDatos.getDatabase(requireContext()).getTaskDao())
    }

    private fun initListener() {
        binding.btnAgregarTarea.setOnClickListener{
            val text =  binding.edTarea.text.toString()
            guardarTexto(text)
            binding.edTarea.setText("")
            Toast.makeText(requireContext(), "Se a agregado un texto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarTexto(texto:String) {
        val task = Tarea(texto)
        GlobalScope.launch { repositorio.insertTask(task) }

    }
    private fun loadTasks(){
        val tasks = repositorio.getTareas().observe(requireActivity()){
            val tasksAsText = it.joinToString("\n") { it.nombre }
            binding.textView.text = tasksAsText
        }


    }

}