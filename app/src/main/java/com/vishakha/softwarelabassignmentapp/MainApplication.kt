package com.vishakha.softwarelabassignmentapp

import android.app.Application
import androidx.room.Room
import com.vishakha.softwarelabassignmentapp.database.AppDatabase

class MainApplication : Application() {

    companion object {
        // Yeh static variable banaya hai taaki kahin bhi database access kar sako
        lateinit var database: AppDatabase
            private set  // sirf yahin se set ho, baahar se change na ho
    }

    override fun onCreate() {
        super.onCreate()

        // Yahan Room database ko build kar rahe hain (ek baar hi start hone pe)
        database = Room.databaseBuilder(
            applicationContext,               // context
            AppDatabase::class.java,          // tumhara @Database class
            "farmer_eats_db"                  // database ka file name (jo phone mein banega)
        )
            // .fallbackToDestructiveMigration()   // agar version change ho toh (development mein use kar sakte ho)
            // .allowMainThreadQueries()           // sirf testing ke liye, production mein mat use karna
            .build()
    }
}