package com.vishakha.softwarelabassignmentapp.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int = 1, // single user
    val token: String
)