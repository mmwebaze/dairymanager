package dev.mwebaze.dairymanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mwebaze.dairymanager.model.DairyCow

/**
 * @author Michael Mwebaze
 */
@Dao
interface DairyCowDao {

    @Query("SELECT * FROM dairy_cows")
    fun getAll() : List<DairyCow>

    @Query("SELECT * FROM dairy_cows WHERE tag_id = :tagId")
    fun findByTagId(tagId: Int): DairyCow

    @Query("DELETE FROM dairy_cows")
    fun deleteAllCows()

    @Insert
    fun insert(dairyCow: DairyCow)
}