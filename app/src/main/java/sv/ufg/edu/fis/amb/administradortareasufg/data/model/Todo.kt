package sv.ufg.edu.fis.amb.administradortareasufg.data.model

import java.util.Date
import java.util.UUID

data class Todo(
    val id: UUID = UUID.randomUUID(),
    val topic: String,
    val description: String,
    val priority: TodoPriority,
    val status: TodoStatus,
    val doDate: Date,
    val dateRange: DateRange
)


