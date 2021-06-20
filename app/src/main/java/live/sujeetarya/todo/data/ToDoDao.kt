package live.sujeetarya.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import live.sujeetarya.todo.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(item: ToDoData)

    @Update
    suspend fun updateData(item: ToDoData)

    @Delete
    suspend fun deleteData(item: ToDoData)

    @Query("Delete From todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE TITLE LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun shortByHighPriority():LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun shortByLowPriority():LiveData<List<ToDoData>>
}