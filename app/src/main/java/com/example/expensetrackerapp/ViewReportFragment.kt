package com.example.expensetrackerapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewReportFragment : Fragment() {

    private val TAG = "ViewReportFragment"
    private val viewModel: ExpenseViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: Inflating layout")
        val view = inflater.inflate(R.layout.fragment_view_report, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewExpenses)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.expenses.observe(viewLifecycleOwner, Observer { expenses ->
            Log.d(TAG, "onCreateView: Observing expenses: $expenses")
            adapter = ExpenseAdapter(expenses)
            recyclerView.adapter = adapter
        })

        return view
    }
}
