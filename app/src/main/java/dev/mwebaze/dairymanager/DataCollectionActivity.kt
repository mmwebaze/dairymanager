package dev.mwebaze.dairymanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import dev.mwebaze.dairymanager.database.DairyDatabase
import dev.mwebaze.dairymanager.model.DairyCow
import dev.mwebaze.diarymanager.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class DataCollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_collection)
        val cowSpinner: Spinner = findViewById<Spinner>(R.id.spinner_cows)

        Single.fromCallable(){
            val db = DairyDatabase(this)
            db.DiaryCowDao().getAll() ?: ArrayList<DairyCow>()

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = {
                   cows ->
                    val spinnerArrayAdapter = ArrayAdapter(this, R.layout.spinner_item, cows)
                    cowSpinner.setAdapter(spinnerArrayAdapter);

                },
                onError = {
                    error -> Log.e("KITTS", "Couldn't read cow list from database", error)
                }
            )
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onRestart() {
        super.onRestart()
        val dataCollectionIntent = Intent(this, DataCollectionActivity::class.java)
        finish()
        overridePendingTransition(0,0)
        startActivity(dataCollectionIntent)
        overridePendingTransition(0, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.logout -> {
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.search -> {
                //Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                val searchIntent = Intent(this, SearchActivity::class.java)
                startActivity(searchIntent)
                true
            }
            R.id.help -> {
                Toast.makeText(this, "HELP", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cow_management -> {
                val cowManagementIntent = Intent(this, DairyCowManagementActivity::class.java)
                startActivity(cowManagementIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun save(view: View){
        val cowSpinner: Spinner = findViewById<Spinner>(R.id.spinner_cows)
        val (tagId, name) = cowSpinner.selectedItem as DairyCow

        val periodCollectionGroup: RadioGroup =  findViewById<RadioGroup>(R.id.periodCollection);
        val selectedId = periodCollectionGroup.checkedRadioButtonId;
        val radioCollectionPeriodButton : RadioButton =  findViewById<RadioButton>(selectedId);

        val amountCollected : EditText = findViewById(R.id.collected_amount)

        Toast.makeText(this, radioCollectionPeriodButton.text.toString()+" - "+amountCollected.text.toString(), Toast.LENGTH_SHORT).show();
    }
}