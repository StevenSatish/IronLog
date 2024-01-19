package com.example.ironlog

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp

@Database(
    entities = [Exercise::class],
    version = 1
)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

}
@HiltAndroidApp
class IronLogApp : Application()
