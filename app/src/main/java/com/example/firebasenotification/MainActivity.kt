package com.example.firebasenotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebasenotification.databinding.ActivityMainBinding
import com.example.firebasenotification.models.Data
import com.example.firebasenotification.models.MyResponse
import com.example.firebasenotification.models.Sender
import com.example.firebasenotification.retrofit.ApiClient
import com.example.firebasenotification.retrofit.ApiService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getToken()

        val apiService = ApiClient.getRetrofit("https://fcm.googleapis.com/").create(ApiService::class.java)

        binding.apply {
            btnSend.setOnClickListener {

                apiService.sendNotification(
                    Sender(
                        Data(
                            "1-emulatordan",
                            R.drawable.ic_launcher_background,
                            "darsda sinov uchun notification",
                            "Sarlavha",
                            "2-emualotrga"
                        ),
                        "c3dMAOYWRamtmoSbLi0Wj0:APA91bHj5-feq8ENZYkBFuJXbw_rdbCKS2daGL8wem0xPxxzOLpRS5YRqjZO5MLpWzTiYYMmaZZk6I5HxOlEvDPgRy3GFibNqjb6CUDBPrIDuGoQTxZtJWQV1mkm5Z59B4SXRhO9pWrM"
                    )
                ).enqueue(object : Callback<MyResponse>{
                    override fun onResponse(
                        call: Call<MyResponse>,
                        response: Response<MyResponse>,
                    ) {
                        Toast.makeText(this@MainActivity, "Responce bo'ldi", Toast.LENGTH_SHORT)
                            .show()
                        if (response.isSuccessful){
                            Toast.makeText(this@MainActivity, "Notification sent", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }
    }

    private fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task->
            if (!task.isSuccessful){
                Log.d(TAG, "onCreate: token falled")
            }
            val token = task.result
            Log.d(TAG, token ?: "")
            binding.tvToken.text = token
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })
    }
}