package one.digitalinnovation.listacontatos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class contact(
    var name: String,
    var phone: String,
    var photo: String
) : Parcelable