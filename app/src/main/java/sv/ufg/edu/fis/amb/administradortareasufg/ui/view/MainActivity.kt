package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import sv.ufg.edu.fis.amb.administradortareasufg.R
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.DateRange
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoPriority
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoStatus
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

                val todo = Todo(
            topic = "Complete assignment",
            description = "Finish the project by Friday",
            status = TodoStatus.started,
            priority = TodoPriority.hard,
            doDate = Date(), // Sample date string
            dateRange = DateRange.thisWeek // Sample date range
        )
        //CODIGO QUE CORRE EL FRAGMENTO AL CARGAR
        // Se le pasa por constructor al fragmento, el todo previamente declarado
        // asi se muestra su info en la view
        val taskDetailsFragment = TaskDetailFragment(todo = todo)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, taskDetailsFragment)
            .commit()
        //----------------------------------------


    }
}