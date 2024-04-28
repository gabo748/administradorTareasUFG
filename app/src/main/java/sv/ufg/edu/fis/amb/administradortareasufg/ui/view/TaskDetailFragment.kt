package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sv.ufg.edu.fis.amb.administradortareasufg.R
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.DateRange
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoPriority
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoStatus
import sv.ufg.edu.fis.amb.administradortareasufg.ui.viewModel.TodoViewModel
import sv.ufg.edu.fis.amb.administradortareasufg.util.MySharedPreferences
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TaskDetailFragment(val todo: Todo?) : Fragment() {
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

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setupBinding()

    }

    private fun setUpUpdatedTaskStates() {
        val statusIndexSelected = getIndexOfCheckBoxSelected(checkBoxList)
        val doDateRangeIndexSelected = getIndexOfCheckBoxSelected(checkBoxList2)
        val priorityIndexSelected = getIndexOfCheckBoxSelected(checkBoxList3)

        if (statusIndexSelected != null) {
            when(statusIndexSelected) {
                0 -> updatedTodoStatus = TodoStatus.started
                1 -> updatedTodoStatus = TodoStatus.inProgress
                2 -> updatedTodoStatus = TodoStatus.Completed
            }
        }

        if (doDateRangeIndexSelected != null) {
            when(doDateRangeIndexSelected) {
                0 -> updatedDateRange = DateRange.today
                1 -> updatedDateRange = DateRange.thisWeek
                2 -> updatedDateRange = DateRange.twoWeeks
            }
        }

        if (priorityIndexSelected != null) {
            when(priorityIndexSelected) {
                0 -> updatedPriority = TodoPriority.normal
                1 -> updatedPriority = TodoPriority.medium
                2 -> updatedPriority = TodoPriority.hard
            }
        }
    }

    private fun saveOrUpdateTodoAction() {
        btnSaveTodo.setOnClickListener {
            val statusIndexSelected = getIndexOfCheckBoxSelected(checkBoxList)
            val doDateRangeIndexSelected = getIndexOfCheckBoxSelected(checkBoxList2)
            val priorityIndexSelected = getIndexOfCheckBoxSelected(checkBoxList3)

            // validar que no hayan campos vacios
            if (
                statusIndexSelected == null ||
                doDateRangeIndexSelected == null ||
                priorityIndexSelected == null ||
                taskNameEditText.text.toString().isEmpty() ||
                taskDescriptionEditText.text.toString().isEmpty() ||
                dateTextView.text.toString().isEmpty()
                ) {
                Toast.makeText(context, "POR FAVOR NO DEJES CAMPOS VACIOS", Toast.LENGTH_SHORT).show()
            } else {
                setUpUpdatedTaskStates()

                val updatedTodo = Todo(
                    topic = taskNameEditText.text.toString(),
                    description = taskDescriptionEditText.text.toString(),
                    priority = updatedPriority,
                    status = updatedTodoStatus,
                    doDate = updatedTodoDate,
                    dateRange = updatedDateRange
                )

                if (todo == null) {
                    viewModel.insertTodo(requireContext(), updatedTodo)
                } else {
                    viewModel.updateTodo(requireContext(), updatedTodo)
                }
            }
        }
    }

    private fun getIndexOfCheckBoxSelected(checkBoxList: List<CheckBox>): Int? {
        var selectedIndex: Int? = null
        for ((index, checkbox) in checkBoxList.withIndex()) {
            if (checkbox.isChecked) {
                selectedIndex = index
            }
        }
        return selectedIndex
    }

    private fun showDatePickerAction() {
        btnShowDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun setupBinding() {
        val todosString = MySharedPreferences.getJsonData(requireContext())
        Log.d("StoredTodo", "Todo : ${todosString}")
        if (todosString != null) {
            val typeToken = object : TypeToken<MutableList<Todo>>() {}.type
            val todos: MutableList<Todo> = Gson().fromJson(todosString, typeToken)
            viewModel.setTodos(todos)

            Log.d("Todos", "The value of x is: ${viewModel.todos.value}")

        }
    }

    private fun setCheckBoxesData(view: View) {
        if (todo != null) {
            when(todo.status) {
                TodoStatus.started -> checkBox.isChecked = true
                TodoStatus.inProgress -> checkBox2.isChecked = true
                TodoStatus.Completed -> checkBox3.isChecked = true
            }

            when(todo.dateRange) {
                DateRange.today ->  checkBox4.isChecked = true
                DateRange.thisWeek -> checkBox5.isChecked = true
                DateRange.twoWeeks -> checkBox6.isChecked = true
            }

            when(todo.priority) {
                TodoPriority.normal -> checkBox7.isChecked = true
                TodoPriority.medium -> checkBox8.isChecked = true
                TodoPriority.hard -> checkBox9.isChecked = true
            }
        }

    }

    private fun setupCheckBoxes(view: View) {
        checkBoxList = listOf(checkBox, checkBox2, checkBox3)
        checkBoxList2 = listOf(checkBox4, checkBox5, checkBox6)
        checkBoxList3 = listOf(checkBox7, checkBox8, checkBox9)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox5.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox6.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList2.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox7.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox8.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

        checkBox9.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxList3.forEach { cb ->
                    if (cb != buttonView && cb.isChecked) {
                        cb.isChecked = false
                    }
                }
            }
        }

    }

    private fun setTextsViewData(view: View) {
        if (todo != null) {
            taskNameEditText.setText(todo.topic)
            taskDescriptionEditText.setText(todo.description)
            updatedTodoDate = todo.doDate
            dateTextView.text = "Fecha seleccionada: ${getCustomDateFormat(todo.doDate)}"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextsViewData(view)
        setCheckBoxesData(view)
    }

    private fun getCustomDateFormat(date: Date): String {
        var spanishLocale = Locale("es", "ES")
        val simpleDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", spanishLocale)
        return simpleDateFormat.format(date.time)
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updatedTodoDate = date.time
                dateTextView.text = "Fecha seleccionada:  ${getCustomDateFormat(date.time)}"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.task_details_fragment, container, false)

        // TextViews
        dateTextView = view.findViewById<TextView>(R.id.date_selected_textView)

        // Buttons
        btnShowDatePicker = view.findViewById<Button>(R.id.date_picker_button)
        btnSaveTodo = view.findViewById<Button>(R.id.save)

        // CheckBoxes
        checkBox = view.findViewById<MaterialCheckBox>(R.id.status1)
        checkBox2 = view.findViewById<MaterialCheckBox>(R.id.status2)
        checkBox3 = view.findViewById<MaterialCheckBox>(R.id.status3)
        checkBox4 = view.findViewById<MaterialCheckBox>(R.id.ck_today)
        checkBox5 = view.findViewById<MaterialCheckBox>(R.id.ck_thisWeek)
        checkBox6 = view.findViewById<MaterialCheckBox>(R.id.ck_twoWeeks)
        checkBox7 = view.findViewById<MaterialCheckBox>(R.id.priority1)
        checkBox8 = view.findViewById<MaterialCheckBox>(R.id.priority2)
        checkBox9 = view.findViewById<MaterialCheckBox>(R.id.priority3)

        // Edit text
        taskNameEditText = view.findViewById<TextInputEditText>(R.id.task_name)
        taskDescriptionEditText = view.findViewById<TextInputEditText>(R.id.task_description)

        setupCheckBoxes(view)
        showDatePickerAction()
        saveOrUpdateTodoAction()

        return view
    }
}

