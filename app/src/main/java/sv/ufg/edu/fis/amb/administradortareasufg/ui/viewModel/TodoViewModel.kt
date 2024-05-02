package sv.ufg.edu.fis.amb.administradortareasufg.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.DateRange
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.Todo
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoPriority
import sv.ufg.edu.fis.amb.administradortareasufg.data.model.TodoStatus
import sv.ufg.edu.fis.amb.administradortareasufg.data.repository.TodoRepository

class TodoViewModel(): ViewModel() {
    private val repository = TodoRepository()

    private val _todos: MutableLiveData<MutableList<Todo>> = MutableLiveData()
    val todos: LiveData<MutableList<Todo>> = _todos

    fun updateTodo(context: Context, todo: Todo) {
        repository.updateTodos(todo, context)
    }

    fun insertTodo(context: Context, todo: Todo) {
        repository.insertTodo( todo, context)
    }

    fun deleteTodo(context: Context, todo: Todo) {
        repository.deleteTodo(todo, context)
    }

    fun setTodos(context: Context) {
        _todos.value = repository.getTodos(context)
    }

    fun updateTodosByStatus(context: Context, status: TodoStatus) {
        _todos.value = repository.getTodos(context)?.filter { it.status == status }?.toMutableList()
    }

    fun updateTodosByDateRange(context: Context, dateRange: DateRange) {
        _todos.value = repository.getTodos(context)?.filter { it.dateRange == dateRange }?.toMutableList()
    }

    fun updateTodosByPriority(context: Context, priority: TodoPriority) {
        _todos.value = repository.getTodos(context)?.filter { it.priority == priority }?.toMutableList()
    }

}