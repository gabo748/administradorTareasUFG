package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Compañero Digital"
        supportActionBar?.setIcon(R.drawable.logo)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "It will open a new fragment 🚀", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
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