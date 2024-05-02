package sv.ufg.edu.fis.amb.administradortareasufg.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.util.MySharedPreferences

class TodoRepository {
    private val jsonParser = Gson()

    fun updateTodos(
        todoToUpdate: Todo,
        context: Context
    ) {
        val todos = getTodos(context)
        if (todos != null) {
            Log.d("List", "${todos}")
            Log.d("Selected", "${todoToUpdate}")
            val index = todos.indexOfFirst { it.id == todoToUpdate.id }
            Log.d("indexSelected", "${index}")
            if (index != -1) {
                todos[index] = todoToUpdate
                // Save the updated todos list to SharedPreferences or perform any other necessary actions
                MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
            }
        }

    }

    fun deleteTodo(
        todoToDelete: Todo,
        context: Context
    ) {
        val todos = getTodos(context)
        if (todos != null) {
            todos.removeIf { it.id == todoToDelete.id }
            MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
        }
    }

    fun insertTodo(
        todoToInsert: Todo,
        context: Context
    ) {
        val todos = getTodos(context)
        if (todos != null) {
            todos.add(todoToInsert)
            MySharedPreferences.saveJsonData(context, jsonParser.toJson(todos))
        }
    }

    fun getTodos(context: Context): MutableList<Todo>? {
        val todosJSON = MySharedPreferences.getJsonData(context)
        if (todosJSON != null) {
            val typeToken = object : TypeToken<MutableList<Todo>>() {}.type
            return Gson().fromJson(todosJSON, typeToken)
        } else {
            return null
        }
    }
}