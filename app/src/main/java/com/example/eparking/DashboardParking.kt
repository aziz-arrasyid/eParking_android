package com.example.eparking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardParking : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_jukir)

        recyclerView = findViewById(R.id.table_recycler_view) // Pastikan bahwa ini adalah ID yang sesuai di XML Anda
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterData(ArrayList()) // Inisialisasi adapter dengan data awal kosong
        recyclerView.adapter = adapter

        val loginResponse = intent.getParcelableExtra<LoginResponse>("loginResponse")
        val id = loginResponse?.id // Mendapatkan nilai id dari loginResponse

        val service = RetrofitClient.retrofit.create(Api::class.java)
        val call = service.getTable(id ?: "")

        call.enqueue(object : Callback<TableResponse> {
            override fun onResponse(call: Call<TableResponse>, response: Response<TableResponse>) {
                if(response.isSuccessful)
                {
                    val responseData  = response.body()


                    if(responseData  != null)
                    {
                        val dataList = listOf(responseData)
                        adapter.setData(dataList)
                    }
                }
            }

            override fun onFailure(call: Call<TableResponse>, t: Throwable) {
                // Handle kegagalan jaringan atau permintaan
            }
        })
    }
}