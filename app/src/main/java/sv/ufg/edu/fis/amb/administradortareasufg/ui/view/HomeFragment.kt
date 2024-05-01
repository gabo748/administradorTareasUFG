package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño XML de tu fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        // Encontrar el Floating Action Button por su ID
        val fab: FloatingActionButton = view.findViewById(R.id.fab)

        // Configurar un OnClickListener para el FAB
        fab.setOnClickListener {
            val fragmentManager = parentFragmentManager.beginTransaction()
            val taskFragment = TaskDetailFragment(todo = null, isDeleteButtonHidden = true)
            fragmentManager.replace(R.id.fragment_container_view, taskFragment)
            fragmentManager.commit()
        }


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

        // Retrieve todos from SharedPreferences and set them in ViewModel
        val todosString = MySharedPreferences.getJsonData(requireContext())
        if (todosString != null) {
            val typeToken = object : TypeToken<MutableList<Todo>>() {}.type
            val todos: MutableList<Todo> = Gson().fromJson(todosString, typeToken)
            // Now you have the parsed list of todos
            todoViewModel.setTodos(todos)
        }
    }

    private fun updateUI(todos: MutableList<Todo>) {
        val container = view?.findViewById<LinearLayout>(R.id.cards_container)
        container?.removeAllViews()

        todos.forEach { todo ->
            val cardView = layoutInflater.inflate(R.layout.card_view, container, false)
            val titleTextView = cardView.findViewById<TextView>(R.id.title)
            titleTextView.text = todo.topic

            // Get reference to the ImageButton
            val imageButton = cardView.findViewById<ImageButton>(R.id.status_image)

            // Set OnClickListener to the ImageButton
            imageButton.setOnClickListener {
                val fragmentManager = parentFragmentManager.beginTransaction()
                val taskFragment = TaskDetailFragment(todo = todo, isDeleteButtonHidden = false)
                fragmentManager.replace(R.id.fragment_container_view, taskFragment)
                fragmentManager.commit()
            }

            container?.addView(cardView)
        }
    }


}