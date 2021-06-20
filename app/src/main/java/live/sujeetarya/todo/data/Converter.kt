package live.sujeetarya.todo.data

import androidx.room.TypeConverter
import live.sujeetarya.todo.data.models.Priority

// Room provides functionality for converting between primitive & boxed types
// but doesn't allow for object references between entities
class Converter {

    // From Priority to String whenever we insert data
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    // From String to Priority whenever we read data
    @TypeConverter
    fun fromString(string: String): Priority {
        return Priority.valueOf(string)
    }
}