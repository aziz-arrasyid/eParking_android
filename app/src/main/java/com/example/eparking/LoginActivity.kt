package com.example.eparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val userName: EditText = findViewById(R.id.username)
        val passWord: EditText = findViewById(R.id.password)

        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = userName.text.toString()
            val password = passWord.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                val alertDialog = AlertDialog.Builder(this@LoginActivity)
                var title = "Username dan password kosong"
                var message = "Mohon isi username dan password"
                // Handle input kosong
                if(username.isEmpty() && password.isNotEmpty()){
                    title = "Username kosong"
                    message = "Mohon isi username"
                }else if(password.isEmpty() && username.isNotEmpty()){
                    title = "Password kosong"
                    message = "Mohon isi password"
                }
                alertDialog.setTitle(title)
                alertDialog.setMessage(message)
                alertDialog.setPositiveButton("OK") { dialog, _ ->
                    // Tindakan saat tombol OK ditekan
                    dialog.dismiss()
                }
                alertDialog.show()
            }else{
                val service = RetrofitClient.retrofit.create(Api::class.java)
                val call = service.authenticate(username, password)

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            val intent: Intent
                            if (loginResponse?.role == "admin") {
                                // Arahkan ke halaman admin
//                                startActivity(Intent(this@LoginActivity, DashboardAdmin::class.java))
                                intent = Intent(this@LoginActivity, DashboardAdmin::class.java)
                                startActivity(intent)
                            } else if (loginResponse?.role == "jukir") {
                                // Arahkan ke halaman pengguna biasa
                                intent = Intent(this@LoginActivity, DashboardParking::class.java)

//                                startActivity(Intent(this@LoginActivity, DashboardParking::class.java))
                                intent.putExtra("data", loginResponse)
                                startActivity(intent)
                            }
                        } else {
                            // Handle pesan kesalahan, misalnya, tampilkan pesan kesalahan kepada pengguna
                            val alertDialog = AlertDialog.Builder(this@LoginActivity)
                            alertDialog.setTitle("Login Gagal")
                            alertDialog.setMessage("Username atau password salah.")
                            alertDialog.setPositiveButton("OK") { dialog, _ ->
                                // Menutup AlertDialog saat tombol OK ditekan
                                dialog.dismiss()
                            }
                            alertDialog.show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        // Handle kegagalan jaringan atau permintaan
                    }
                })
            }
        }
    }
}