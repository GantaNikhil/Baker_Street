package com.example.baker_street.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.baker_street.RetroInstance
import com.example.baker_street.models.AnnouncementsModel
import com.example.baker_street.models.CoursesModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassroomRepo {

    private lateinit var instance: ClassroomRepo
    private val message = MutableLiveData<String>()
    private val announcementModel = MutableLiveData<AnnouncementsModel>()

    fun getInstance(): ClassroomRepo {
        instance = ClassroomRepo()
        return instance
    }

    fun getAnnouncements(jwtToken: String,courseid:String) {
        GlobalScope.launch {
            RetroInstance.api.getAnnouncements("Bearer $jwtToken", courseid)
                .enqueue(object : Callback<AnnouncementsModel> {
                    override fun onResponse(
                        call: Call<AnnouncementsModel>,
                        response: Response<AnnouncementsModel>
                    ) {
                        try {
                            announcementModel.postValue(response.body())
                            Log.d("obul",response.toString())
                            message.postValue("OKAnnouncements")
                        } catch (E: Exception) {
                            announcementModel.postValue(response.body())
                            Log.d("obul2",E.toString())
                            message.postValue("ErrorAnnouncements")
                        }
                    }

                    override fun onFailure(call: Call<AnnouncementsModel>, t: Throwable) {
                        message.postValue(t.toString())
                        Log.d("obul3", t.toString())
                    }
                })
        }
    }
    fun getMessageObserver(): MutableLiveData<String> {
        return message
    }
    fun getAnnouncementsObserver(): MutableLiveData<AnnouncementsModel> {
        return announcementModel
    }
}