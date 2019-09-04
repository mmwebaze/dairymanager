package dev.mwebaze.diarymanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import dev.mwebaze.diarymanager.model.DiaryCow
import dev.mwebaze.diarymanager.service.DataManager
import dev.mwebaze.diarymanager.service.DataManagerInterface


class DataCollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_collection)
        val cowSpinner: Spinner = findViewById<Spinner>(R.id.spinner_cows)

        val dataManager : DataManagerInterface = DataManager()
        val milkingAnimals = dataManager.getMilkingAnimals()

        val spinnerArrayAdapter = ArrayAdapter(this, R.layout.spinner_item, milkingAnimals)
        cowSpinner.setAdapter(spinnerArrayAdapter);

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
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
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun save(view: View){
        val cowSpinner: Spinner = findViewById<Spinner>(R.id.spinner_cows)
        //val milkedCow : DiaryCow
        val (tagId, name) = cowSpinner.selectedItem as DiaryCow

        val periodCollectionGroup: RadioGroup =  findViewById<RadioGroup>(R.id.periodCollection);
        val selectedId = periodCollectionGroup.checkedRadioButtonId;
        val radioCollectionPeriodButton : RadioButton =  findViewById<RadioButton>(selectedId);

        val amountCollected : EditText = findViewById(R.id.collected_amount)

        Toast.makeText(this, radioCollectionPeriodButton.text.toString()+" - "+amountCollected.text.toString(), Toast.LENGTH_SHORT).show();
    }
}