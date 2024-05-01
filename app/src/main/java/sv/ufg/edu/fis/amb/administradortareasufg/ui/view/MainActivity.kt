package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sv.ufg.edu.fis.amb.administradortareasufg.R
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.DateRange
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoPriority
import sv.ufg.edu.fis.amb.administradortareasufg.ui.viewModel.TodoViewModel
import sv.ufg.edu.fis.amb.administradortareasufg.util.MySharedPreferences
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.commit
import androidx.fragment.app.replace



class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Digital Partner"
        supportActionBar?.setIcon(R.drawable.logo)

        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }


        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        val todosString = MySharedPreferences.getJsonData(this)
        if (todosString != null) {
            val typeToken = object : TypeToken<MutableList<Todo>>() {}.type
            val todos: MutableList<Todo> = Gson().fromJson(todosString, typeToken)
            viewModel.setTodos(todos)

            Log.d("Todos", "The value of x is: ${viewModel.todos.value}")

        }

        // observer to update the UI
        viewModel.todos.observe(this, Observer { todos ->

        })
    }

}