package com.daniel.padilla.compactpos.database.entities

import android.graphics.Bitmap
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore

class ProductoObject: RealmObject {

    var name: String = ""
    var categoria: String = ""
    var descripcion: String = ""
    var tipo: RealmList<String> = realmListOf()
    var marca: String? = null
    var codigoBarras: String? = null
    var preparacion: String = ""
    var precio: String = ""
    @Ignore var imageBitmap: Bitmap? = null



}