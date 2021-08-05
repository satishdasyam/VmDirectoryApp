package com.dasyam.vmdirectoryapp.ofc_rooms

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfcRoom(
    @Expose var name: String = "",
    @Expose @SerializedName("is_occupied") var isOccupied: Boolean = false,
    @Expose @SerializedName("max_occupancy") var maxOccupancy: String = ""
): Parcelable
