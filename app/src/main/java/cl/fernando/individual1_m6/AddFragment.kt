package cl.fernando.individual1_m6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        initListener()
        loadTasks()
        return binding.root
    }

    private fun initListener() {
        binding.btnAgregarTarea.setOnClickListener{
            val text =  binding.edTarea.text.toString()
            guardarTarea(text)
        }
    }

    private fun guardarTarea(texto:String) {
        val dao = TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        val task = Tarea(texto)
        GlobalScope.launch { dao.insertarTarea(task) }

    }
    private fun loadTasks(){
        val dao = TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        GlobalScope.launch {
            val tasks = dao.getTareas()
            val tasksAsText = tasks.joinToString("\n") { it.nombre }

            binding.textView.text = tasksAsText
        }

    }

}