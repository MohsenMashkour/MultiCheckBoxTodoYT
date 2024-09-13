package com.mkrdeveloper.multicheckboxtodoyt.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkrdeveloper.multicheckboxtodoyt.data.dao.TodoDao
import com.mkrdeveloper.multicheckboxtodoyt.data.entity.TodoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val todoDao: TodoDao) : ViewModel() {
    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> = _todos

    init {
        viewModelScope.launch {
            todoDao.getAllTodosOrderedById().collect { todos ->
                _todos.value = todos
            }
        }
    }

    fun insertTodo(todoTitle: String) {
        val todo = TodoEntity(title = todoTitle, statuses = List(3) { false })
        viewModelScope.launch {
            todoDao.upsert(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            todoDao.delete(todo)
        }
    }

    fun updateTodo(todoEntity: TodoEntity, newStatuses: List<Boolean>) {
        viewModelScope.launch {
            todoDao.upsert(todoEntity.copy(statuses = newStatuses))
        }
    }
}
