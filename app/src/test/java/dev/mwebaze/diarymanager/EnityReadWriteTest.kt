package dev.mwebaze.diarymanager

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import androidx.room.Room
import dev.mwebaze.dairymanager.dao.DairyCowDao
import dev.mwebaze.dairymanager.database.DairyDatabase
import dev.mwebaze.dairymanager.model.DairyCow
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EnityReadWriteTest {

    private lateinit var db: DairyDatabase
    private lateinit var diaryCowDao: DairyCowDao


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(
            context, DairyDatabase::class.java).build()
        diaryCowDao = db.DiaryCowDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val kyasha: DairyCow = DairyCow(1234, "Kyasha")
        diaryCowDao.insert(kyasha)
        val kyashaRet = diaryCowDao.findByTagId(kyasha.tagId)
        assertThat(kyashaRet, equalTo(kyasha))
    }
}