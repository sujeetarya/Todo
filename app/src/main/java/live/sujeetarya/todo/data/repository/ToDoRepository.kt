package live.sujeetarya.todo.data.repository

import androidx.lifecycle.LiveData
import live.sujeetarya.todo.data.ToDoDao
import live.sujeetarya.todo.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()
    val shortByHighPriority:LiveData<List<ToDoData>> = toDoDao.shortByHighPriority()
    val shortByLowPriority:LiveData<List<ToDoData>> = toDoDao.shortByLowPriority()

    suspend fun insertData(toDoData: ToDoData) = toDoDao.insertData(toDoData)

    suspend fun updateData(toDoData: ToDoData) = toDoDao.updateData(toDoData)

    suspend fun deleteData(toDoData: ToDoData) = toDoDao.deleteData(toDoData)

    suspend fun deleteAll() = toDoDao.deleteAll()

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> = toDoDao.searchDatabase(searchQuery)
}