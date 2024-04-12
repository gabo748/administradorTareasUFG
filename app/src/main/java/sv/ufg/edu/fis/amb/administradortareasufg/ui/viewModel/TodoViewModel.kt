package sv.ufg.edu.fis.amb.administradortareasufg.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.repository.TodoRepository

class TodoViewModel(): ViewModel() {
    private val repository = TodoRepository()

    private val _todos: MutableLiveData<MutableList<Todo>> = MutableLiveData()
    val todos: LiveData<MutableList<Todo>> = _todos

    fun updateTodo(context: Context, todo: Todo) {
        repository.updateTodos(_todos.value?.toMutableList() ?: mutableListOf(), todo, context)
    }

    fun insertTodo(context: Context, todo: Todo) {
        repository.insertTodo(_todos.value?.toMutableList() ?: mutableListOf(), todo, context)
    }

    fun deleteTodo(context: Context, todo: Todo) {
        repository.deleteTodo(_todos.value?.toMutableList() ?: mutableListOf(), todo, context)
    }

    fun setTodos(todos: MutableList<Todo>) {
        _todos.value = todos
    }
}