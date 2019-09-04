package dev.mwebaze.diarymanager

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import dev.mwebaze.diarymanager.adaptor.SearchResultsRecyclerViewAdaptor
import dev.mwebaze.diarymanager.service.DataManager
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_linear_layout.*
import java.util.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val calendar = Calendar.getInstance()
        val year =  calendar.get(Calendar.YEAR)
        val month =  calendar.get(Calendar.MONTH)
        val day =  calendar.get(Calendar.DAY_OF_MONTH)
        var datum: String;

        selected_date.text = ""+day+"/"+month+"/"+year

        date_btn.setOnClickListener(){
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view : DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                datum = ""+mDay+"/"+mMonth+"/"+mYear
                //val selectedDate = findViewById<TextView>(R.id.selected_date)
                selected_date.text = datum
                //Toast.makeText(this, datum, Toast.LENGTH_SHORT).show()
                val cows = DataManager()
                val cowsList = cows.getMilkingAnimals()
                val tags : ArrayList<Int> = ArrayList<Int>()
                val names : ArrayList<String> = ArrayList<String>()
                val amountMilk : ArrayList<Double> = ArrayList<Double>()

                for (cow in cowsList){
                    tags.add(cow.tagId)
                    names.add(cow.name)
                    amountMilk.add(cow.tagId * 2.5)
                }

                val recyclerView = findViewById<RecyclerView>(R.id.search_results_recyclerview)
                recyclerView.adapter = SearchResultsRecyclerViewAdaptor(this, tags, names, amountMilk)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            }, year, month, day)

            datePicker.show()
        }
    }
}
