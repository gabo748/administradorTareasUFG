package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.checkbox.MaterialCheckBox
import sv.ufg.edu.fis.amb.administradortareasufg.R
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [task_details_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class task_details_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.task_details_fragment, container, false)

        val checkBox = view.findViewById<MaterialCheckBox>(R.id.status1)
        val checkBox2 = view.findViewById<MaterialCheckBox>(R.id.status2)
        val checkBox3 = view.findViewById<MaterialCheckBox>(R.id.status3)

        val checkBoxList = listOf(checkBox, checkBox2, checkBox3)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea está próxima a empezar", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea está en proceso...", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea ha sido completada!", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }
}

