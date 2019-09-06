package dev.mwebaze.dairymanager.model

import android.support.annotation.NonNull
import androidx.room.*

/**
 * @author Michael Mwebaze
 */
@Entity(tableName = "dairy_cows", indices = [Index(value = ["tag_id"], unique = true)])
data class DairyCow (@param:NonNull @field:ColumnInfo(name = "tag_id") var tagId: Int,
                     @param:NonNull @field:ColumnInfo(name = "name") var name: String,
                     @field:ColumnInfo(name = "milking_status") var isMilked: Boolean = true) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {

        return this.tagId.toString();
    }
}
