package dev.mwebaze.diarymanager

import android.support.test.InstrumentationRegistry
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
import org.powermock.api.mockito.PowerMockito

import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.io.IOException

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class EnityReadWriteTest {

    private lateinit var db: DairyDatabase
    private lateinit var diaryCowDao: DairyCowDao


    @Before
    fun createDb() {
        PowerMockito.mockStatic(Log::class.java)
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