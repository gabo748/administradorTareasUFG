package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.checkbox.MaterialCheckBox
import sv.ufg.edu.fis.amb.administradortareasufg.R
    // Binding
    private lateinit var viewModel: TodoViewModel
    private lateinit var updatedPriority: TodoPriority
    private lateinit var updatedDateRange: DateRange
    private lateinit var updatedTodoStatus: TodoStatus
    private lateinit var updatedTodoDate: Date

    // Buttons
    private lateinit var btnShowDatePicker: Button
    private lateinit var btnSaveTodo: Button

    // TextViews
    private lateinit var dateTextView: TextView

    // CheckBox
    private  lateinit var checkBox: MaterialCheckBox
    private  lateinit var checkBox2: MaterialCheckBox
    private  lateinit var checkBox3: MaterialCheckBox
    private  lateinit var checkBox4: MaterialCheckBox
    private  lateinit var checkBox5: MaterialCheckBox
    private  lateinit var checkBox6: MaterialCheckBox
    private  lateinit var checkBox7: MaterialCheckBox
    private  lateinit var checkBox8: MaterialCheckBox
    private  lateinit var checkBox9: MaterialCheckBox

    // CheckBoxList
    private  lateinit var checkBoxList: List<MaterialCheckBox>
    private  lateinit var checkBoxList2: List<MaterialCheckBox>
    private  lateinit var checkBoxList3: List<MaterialCheckBox>

    // Edit Text
    private lateinit var taskNameEditText: TextInputEditText
    private lateinit var taskDescriptionEditText: TextInputEditText


    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.task_details_fragment, container, false)

        val checkBox = view.findViewById<MaterialCheckBox>(R.id.status1)
        val checkBox2 = view.findViewById<MaterialCheckBox>(R.id.status2)
        val checkBox3 = view.findViewById<MaterialCheckBox>(R.id.status3)
        val checkBox4 = view.findViewById<MaterialCheckBox>(R.id.ck_today)
        val checkBox5 = view.findViewById<MaterialCheckBox>(R.id.ck_thisWeek)
        val checkBox6 = view.findViewById<MaterialCheckBox>(R.id.ck_twoWeeks)
        val checkBox7 = view.findViewById<MaterialCheckBox>(R.id.priority1)
        val checkBox8 = view.findViewById<MaterialCheckBox>(R.id.priority2)
        val checkBox9 = view.findViewById<MaterialCheckBox>(R.id.priority3)

        val checkBoxList = listOf(checkBox, checkBox2, checkBox3)
        val checkBoxList2 = listOf(checkBox4, checkBox5, checkBox6)
        val checkBoxList3 = listOf(checkBox7, checkBox8, checkBox9)

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

        checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea se entrega HOY!", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox5.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea se entra ESTA SEMANA!", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox6.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "Su tarea se entrega en DOS SEMANAS!", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox7.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "El nivel de prioridad de su tarea es NORMAL!", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox8.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "El nivel de prioridad de su tarea es MEDIA!", Toast.LENGTH_SHORT).show()
            }
        }

        checkBox9.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
                Toast.makeText(context, "El nivel de prioridad de su tarea es ALTA!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

