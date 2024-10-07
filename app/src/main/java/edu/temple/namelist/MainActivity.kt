package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String>  // Use MutableList for easier manipulation
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        adapter = CustomAdapter(names, this)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p0?.run {
                    nameTextView.text = getItemAtPosition(p2).toString()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Handle case when nothing is selected
                nameTextView.text = ""
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            if (selectedPosition >= 0 && names.isNotEmpty()) {
                names.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()
// issue 3 **********
 // If there are still names left select from the first one
                if (names.isNotEmpty()) {
                    spinner.setSelection(0)
                } else {
// Clear the nameTextView if list is empty
                    nameTextView.text = ""
                }
            }
        }
    }
}
