package dev.mwebaze.dairymanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import dev.mwebaze.dairymanager.database.DairyDatabase
import dev.mwebaze.dairymanager.model.DairyCow
import dev.mwebaze.diarymanager.R
import kotlinx.coroutines.*

class DairyCowManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dairy_cow_management_layout)
    }
    fun addCow(view: View) {
        val cowTag = findViewById<EditText>(R.id.add_tag_id)
        val cowName = findViewById<EditText>(R.id.name_of_cow)
        val isMilked = findViewById<CheckBox>(R.id.cow_milked)

        Toast.makeText(this, "COW ADDED: "+cowTag.text+", "+cowName.text+", "+isMilked.isChecked, Toast.LENGTH_SHORT).show()

        val db = DairyDatabase(this)

        GlobalScope.launch {
            db.DiaryCowDao().insert(DairyCow(cowTag.text.toString().toInt(), cowName.text.toString(), isMilked.isChecked))
        }
    }
}
