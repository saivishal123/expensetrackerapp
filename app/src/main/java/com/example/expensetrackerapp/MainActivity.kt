package com.example.expensetrackerapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Activity created")
        if (savedInstanceState == null) {
            // Display AddExpenseFragment as the main home page
            Log.d(TAG, "onCreate: Replacing with AddExpenseFragment")
            replaceFragment(AddExpenseFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_expense -> {
                Log.d(TAG, "onOptionsItemSelected: Add Expense selected")
                replaceFragment(AddExpenseFragment())
                true
            }
            R.id.action_view_report -> {
                Log.d(TAG, "onOptionsItemSelected: View Report selected")
                replaceFragment(ViewReportFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
