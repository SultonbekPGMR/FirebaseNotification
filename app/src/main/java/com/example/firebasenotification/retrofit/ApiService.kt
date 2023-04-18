package com.example.firebasenotification.retrofit

import com.example.firebasenotification.models.MyResponse
import com.example.firebasenotification.models.Sender
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-type:application/json",
        "Authorization:key=AAAAMdR4VYk:APA91bFg04GtGXIaBFzYErdB_U9CoJh5qMayEpX0OL8gmEiQLbNIRPJ-_YQYSH6k-UUGCkgFWPMpmd2OSMlU1Z_i6aH7KFTIf61wd-YfVQfMwJf5n7O5L5SY0Lvk9-fghabXjpLwowR_"
    )
    @POST("fcm/send")
    fun sendNotification(@Body sender: Sender): Call<MyResponse>
}