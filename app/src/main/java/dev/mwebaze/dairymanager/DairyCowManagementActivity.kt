package dev.mwebaze.dairymanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import dev.mwebaze.dairymanager.database.DairyDatabase
import dev.mwebaze.dairymanager.model.DairyCow
import dev.mwebaze.diarymanager.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * @author Michael Mwebaze
 */
class DairyCowManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dairy_cow_management_layout)
    }
    fun addCow(view: View) {
        val cowTag = findViewById<EditText>(R.id.add_tag_id)
        val cowName = findViewById<EditText>(R.id.name_of_cow)
        val isMilked = findViewById<CheckBox>(R.id.cow_milked)

        val dis: Disposable = Single.fromCallable() {
            val db = DairyDatabase(this)
            db.DiaryCowDao().insert(DairyCow(cowTag.text.toString().toInt(), cowName.text.toString(), isMilked.isChecked))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = {
                    Toast.makeText(this, "COW ADDED: "+cowTag.text+", "+cowName.text+", "+isMilked.isChecked, Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(this, cowTag.text.toString()+" NOT AVAILABLE", Toast.LENGTH_LONG).show()
                }
            )
    }
}
