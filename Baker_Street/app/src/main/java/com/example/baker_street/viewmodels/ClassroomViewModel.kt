package com.example.baker_street.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baker_street.models.AnnouncementsModel
import com.example.baker_street.repositories.ClassroomRepo

class ClassroomViewModel : ViewModel() {
    private var message: MutableLiveData<String>? = null
    private var announcements: MutableLiveData<AnnouncementsModel>? = null
    private var repo: ClassroomRepo? = null

    init {
        repo = ClassroomRepo().getInstance()
        message = MutableLiveData<String>()
        announcements = MutableLiveData<AnnouncementsModel>()
    }

    fun getAnnouncements(jwtToken: String, courseid: String) {
        repo?.getAnnouncements(jwtToken, courseid)
    }

    fun getMessageObserver(): MutableLiveData<String>? {
        message = repo?.getMessageObserver()
        return message
    }

    fun getAnnouncementsObserver(): MutableLiveData<AnnouncementsModel>? {
        announcements = repo?.getAnnouncementsObserver()
        return announcements
    }

}