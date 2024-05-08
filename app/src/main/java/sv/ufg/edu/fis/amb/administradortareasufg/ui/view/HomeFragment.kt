package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sv.ufg.edu.fis.amb.administradortareasufg.R
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.DateRange
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoPriority
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoStatus
import sv.ufg.edu.fis.amb.administradortareasufg.ui.viewModel.TodoViewModel
import sv.ufg.edu.fis.amb.administradortareasufg.util.MySharedPreferences
import java.util.Date

class HomeFragment : Fragment() {
    // view model
    private lateinit var todoViewModel: TodoViewModel

    // buttons
    private lateinit var completedFilterBtn: Button
    private lateinit var startedFilterBtn: Button
    private lateinit var inProgressFilterBtn: Button
    private lateinit var todayFilterBtn: Button
    private lateinit var thisWeekFilterBtn: Button
    private lateinit var twoWeeksFilterBtn: Button
    private lateinit var priorityNormalFilterBtn: Button
    private lateinit var priorityMediumFilterBtn: Button
    private lateinit var priorityHardFilterBtn: Button
    private lateinit var allBtn: Button

    // floating Action button
    private lateinit var addTodoFloatingButton: FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewModel binding
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    private fun createNewTodoAction() {
        addTodoFloatingButton.setOnClickListener {
            val fragmentManager = parentFragmentManager.beginTransaction()
            val taskFragment = TaskDetailFragment(todo = null, isDeleteButtonHidden = true)
            fragmentManager.replace(R.id.fragment_container_view, taskFragment)
            fragmentManager.commit()
        }
    }

    // Function to set the background of each button in the list

    private fun setButtonsBackground(selectedButton: Button?) {
        // Define your button list
        val buttonList = listOf(
            completedFilterBtn,
            startedFilterBtn,
            inProgressFilterBtn,
            todayFilterBtn,
            thisWeekFilterBtn,
            twoWeeksFilterBtn,
            priorityNormalFilterBtn,
            priorityMediumFilterBtn,
            priorityHardFilterBtn
        )

        val nonSelectedColor = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.orange))
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.orange)
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.cream)
        buttonList.forEach { button ->
            if (button == selectedButton) {
                val colorStateList = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_selected),
                        intArrayOf()
                    ),
                    intArrayOf(
                        selectedColor,
                        defaultColor
                    )
                )

                selectedButton.backgroundTintList = colorStateList
            } else {
                val colorStateList = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_selected),
                        intArrayOf()
                    ),
                    intArrayOf(
                        defaultColor,
                        selectedColor
                    )
                )
                button.backgroundTintList = colorStateList
            }
        }
    }

    private fun setupFilterStatusButtonAction(button: Button, status: TodoStatus) {
        button.setOnClickListener {
            setButtonsBackground(button)
            todoViewModel.updateTodosByStatus(requireContext(), status)
        }
    }

    private fun setupFilterPriorityButtonAction(button: Button, status: TodoPriority) {
        button.setOnClickListener {
            setButtonsBackground(button)
            todoViewModel.updateTodosByPriority(requireContext(), status)
        }
    }

    private fun setupFilterDateRangeButtonAction(button: Button, status: DateRange) {
        button.setOnClickListener {
            setButtonsBackground(button)
            todoViewModel.updateTodosByDateRange(requireContext(), status)
        }
    }

    private fun setupAllButtonAction() {
        allBtn.setOnClickListener {
            todoViewModel.setTodos(requireContext())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño XML de tu fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // FloatingButton init
        addTodoFloatingButton = view.findViewById(R.id.fab)

        // buttons init
        completedFilterBtn = view.findViewById<Button>(R.id.completed_filter_btn)
        startedFilterBtn = view.findViewById<Button>(R.id.started_filter_btn)
        inProgressFilterBtn = view.findViewById<Button>(R.id.inProgress_filter_btn)
        todayFilterBtn = view.findViewById<Button>(R.id.today_filter_btn)
        thisWeekFilterBtn = view.findViewById<Button>(R.id.thisWeek_filter_btn)
        twoWeeksFilterBtn = view.findViewById<Button>(R.id.twoWeeks_filter_btn)
        priorityNormalFilterBtn = view.findViewById<Button>(R.id.normalPriority_filter_btn)
        priorityMediumFilterBtn = view.findViewById<Button>(R.id.medium_filter_btn)
        priorityHardFilterBtn = view.findViewById<Button>(R.id.hard_filter_btn)
        allBtn = view.findViewById<Button>(R.id.all_btn)


        // action executions
        createNewTodoAction()

        // All todos
        setupAllButtonAction()

        /// filter by status
        setupFilterStatusButtonAction(completedFilterBtn, TodoStatus.Completed)
        setupFilterStatusButtonAction(startedFilterBtn, TodoStatus.started)
        setupFilterStatusButtonAction(inProgressFilterBtn, TodoStatus.inProgress)

        // filter by priority
        setupFilterPriorityButtonAction(priorityNormalFilterBtn, TodoPriority.normal)
        setupFilterPriorityButtonAction(priorityMediumFilterBtn, TodoPriority.medium)
        setupFilterPriorityButtonAction(priorityHardFilterBtn, TodoPriority.hard)

        // filter by date range
        setupFilterDateRangeButtonAction(todayFilterBtn, DateRange.today)
        setupFilterDateRangeButtonAction(twoWeeksFilterBtn, DateRange.twoWeeks)
        setupFilterDateRangeButtonAction(thisWeekFilterBtn, DateRange.thisWeek)

        return view
    }
    private fun setShapeDrawable(imageView: ImageView, colorResId: Int) {
        val shapeDrawable = GradientDrawable()
        shapeDrawable.shape = GradientDrawable.RECTANGLE
        shapeDrawable.setColor(resources.getColor(colorResId))
        shapeDrawable.cornerRadius = resources.getDimension(R.dimen.corner_radius)
        imageView.background = shapeDrawable
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoViewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todos?.let {
                updateUI(todos)
            }
        })

        todoViewModel.setTodos(requireContext())
    }

    private fun updateUI(todos: MutableList<Todo>) {
        val container = view?.findViewById<LinearLayout>(R.id.cards_container)
        container?.removeAllViews()

        todos.forEach { todo ->
            val cardView = layoutInflater.inflate(R.layout.card_view, container, false)
            val titleTextView = cardView.findViewById<TextView>(R.id.title)
            titleTextView.text = todo.topic

            
            cardView.setOnClickListener {
                val fragmentManager = parentFragmentManager.beginTransaction()
                val taskFragment = TaskDetailFragment(todo = todo, isDeleteButtonHidden = false)
                fragmentManager.replace(R.id.fragment_container_view, taskFragment)
                fragmentManager.commit()
            }

            container?.addView(cardView)
        }
    }


}