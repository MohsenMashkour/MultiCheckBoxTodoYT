package com.mkrdeveloper.multicheckboxtodoyt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mkrdeveloper.multicheckboxtodoyt.data.converters.TodoConverters
import com.mkrdeveloper.multicheckboxtodoyt.data.dao.TodoDao
import com.mkrdeveloper.multicheckboxtodoyt.data.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1
)
@TypeConverters(TodoConverters::class)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao
}