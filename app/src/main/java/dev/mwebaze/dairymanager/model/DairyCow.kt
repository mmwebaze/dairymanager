package dev.mwebaze.dairymanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dairy_cows")
data class DairyCow (@ColumnInfo(name = "tag_id") var tagId: Int, @ColumnInfo(name = "name") var name: String,
                     @ColumnInfo(name = "milking_status") var isMilked: Boolean = true) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {

        return this.name;
    }
}