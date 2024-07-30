package com.example.expensetrackerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val expenses: List<Expense>) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expenses[position]
        holder.amountTextView.text = "Amount: ${currentExpense.amount}"
        holder.descriptionTextView.text = "Description: ${currentExpense.description}"
        holder.categoryTextView.text = "Category: ${currentExpense.category}"
        holder.typeTextView.text = "Type: ${currentExpense.type}"
    }

    override fun getItemCount() = expenses.size

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amountTextView: TextView = itemView.findViewById(R.id.textViewAmount)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        val categoryTextView: TextView = itemView.findViewById(R.id.textViewCategory)
        val typeTextView: TextView = itemView.findViewById(R.id.textViewType)
    }
}
