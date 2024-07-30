// ExpenseDialogFragment.kt
package com.example.expensetrackerapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment

class ExpenseDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense_dialog, container, false)
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

            // Handle adding expense logic here

            dismiss()
        }
    }
}
