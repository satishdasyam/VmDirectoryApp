package com.dasyam.vmdirectoryapp.employees

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    @Expose @SerializedName("avatar") var imagePath: String = "",
    @Expose var firstName: String = "",
    @Expose var lastName: String = "",
    @Expose var email: String = "",
    @Expose @SerializedName("phone") var phoneNo: String = "",
    @Expose var jobTitle: String = "",
    @Expose var favouriteColor: String = ""
) : Parcelable {

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}

/*
"avatar": "https://randomuser.me/api/portraits/women/22.jpg",
"phone": "(800) 653-6415",
"firstName": "Isaac",
"id": "14",
"longitude": 88.3378,
"favouriteColor": "#35743c",
"email": "Davin_Schaefer3@yahoo.com",
"jobTitle": "Dynamic Quality Orchestrator",
"createdAt": "2020-12-14T10:10:59.375Z",
"latitude": 22.5411,
"lastName": "Braun"*/
