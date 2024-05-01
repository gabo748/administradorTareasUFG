package sv.ufg.edu.fis.amb.administradortareasufg.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.util.MySharedPreferences

class TodoRepository {
    private val jsonParser = Gson()

    fun updateTodos(
        todos: MutableList<Todo>,
        todoToUpdate: Todo,
        context: Context
    ) {
        // Find the index of the todo to update in the list
        val index = todos.indexOfFirst { it.id == todoToUpdate.id }
        Log.d("INDEX", "${index}")

        // Check if the todo exists in the list
        if (index != -1) {
            // Update the todo with the new values
            todos[index] = todoToUpdate
            // Save the updated todos list to SharedPreferences or perform any other necessary actions
            MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
        }
    }

    fun deleteTodo(
        todos: MutableList<Todo>,
        todoToDelete: Todo,
        context: Context
    ) {
        todos.removeIf { it.id == todoToDelete.id }
        MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
    }

    fun insertTodo(
        todos: MutableList<Todo>,
        todoToInsert: Todo,
        context: Context
    ) {
        todos.add(todoToInsert)
        MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
    }
}