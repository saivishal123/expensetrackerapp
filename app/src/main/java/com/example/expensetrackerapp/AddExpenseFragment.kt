package com.example.expensetrackerapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class AddExpenseFragment : Fragment() {

    private val TAG = "AddExpenseFragment"
    private val viewModel: ExpenseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: Inflating layout")
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)
        setupViews(view)
        return view
    }

    private fun setupViews(view: View) {
        val amountEditText = view.findViewById<EditText>(R.id.editTextAmount)
        val descriptionEditText = view.findViewById<EditText>(R.id.editTextDescription)
        val categorySpinner = view.findViewById<Spinner>(R.id.spinnerCategory)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupExpenseType)
        val addButton = view.findViewById<Button>(R.id.buttonAddExpense)

        // Setup Spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        addButton.setOnClickListener {
            val amount = amountEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val category = categorySpinner.selectedItem.toString()
            val expenseType = when (radioGroup.checkedRadioButtonId) {
                R.id.radioIncome -> "Income"
                R.id.radioExpense -> "Expense"
                else -> ""
            }

            if (amount.isNotEmpty() && description.isNotEmpty() && expenseType.isNotEmpty()) {
                val expense = Expense(amount, description, category, expenseType)
                Log.d(TAG, "setupViews: Adding expense: $expense")
                viewModel.addExpense(expense)
                Toast.makeText(requireContext(), "Expense Added", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "setupViews: Missing fields, cannot add expense")
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
