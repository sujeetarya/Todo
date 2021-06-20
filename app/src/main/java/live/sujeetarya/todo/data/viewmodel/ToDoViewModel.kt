package live.sujeetarya.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.sujeetarya.todo.data.ToDoDatabase
import live.sujeetarya.todo.data.models.ToDoData
import live.sujeetarya.todo.data.repository.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val toDoDao = ToDoDatabase.getDatabase(application).getToDoDao()

    private val repository: ToDoRepository
    val getAllData: LiveData<List<ToDoData>>

    val shortByHighPriority:LiveData<List<ToDoData>>
    val shortByLowPriority:LiveData<List<ToDoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
        shortByHighPriority = repository.shortByHighPriority
        shortByLowPriority = repository.shortByLowPriority
    }

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(toDoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return repository.searchDatabase(searchQuery)
    }
}
