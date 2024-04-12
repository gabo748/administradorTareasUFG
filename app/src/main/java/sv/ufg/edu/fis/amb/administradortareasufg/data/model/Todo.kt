package sv.ufg.edu.fis.amb.administradortareasufg.data.model

import java.util.UUID

data class Todo(
    val id: UUID = UUID.randomUUID(),
    val topic: String,
    val description: String,
    val priority: TodoPriority,
    val doDate: String,
    val dateRange: DateRange,
    val subject: String
)


