package com.example.bai_2_tuan_7

import SinhVien
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SinhVienAdapter
    private lateinit var etSearch: EditText
    private var sinhVienList = listOf(
        SinhVien(hoTen = "Pham Minh Hoang", mssv = "20215582"),
        SinhVien(hoTen = "Nguyen Huy Hoang", mssv = "20211012"),
        SinhVien(hoTen = "Nguyen Van A", mssv = "20211010"),
        SinhVien(hoTen = "Tran Thi B", mssv = "20215592"),
        SinhVien(hoTen = "Le Minh C", mssv = "20215576")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etSearch = findViewById(R.id.etSearch)

        adapter = SinhVienAdapter(sinhVienList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                filterList(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = if (query.length > 2) {
            sinhVienList.filter {
                it.hoTen.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            }
        } else {
            sinhVienList
        }
        adapter.updateList(filteredList)
    }
}
