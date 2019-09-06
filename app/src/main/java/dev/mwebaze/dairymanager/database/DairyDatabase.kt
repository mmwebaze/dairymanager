package dev.mwebaze.dairymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.mwebaze.dairymanager.dao.DairyCowDao
import dev.mwebaze.dairymanager.model.DairyCow

/**
 * @author Michael Mwebaze
 */
@Database(entities = [DairyCow::class], version = 1)
abstract class DairyDatabase : RoomDatabase(){
    abstract fun DiaryCowDao(): DairyCowDao

    companion object {
        @Volatile private var instance: DairyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(
            LOCK
        ) {
            instance
                ?: buildDatabase(context).also { instance = it}
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DairyDatabase::class.java, "diary-management.db")
            .build()
    }
}