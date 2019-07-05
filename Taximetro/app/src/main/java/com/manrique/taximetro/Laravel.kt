package com.manrique.taximetro


import com.google.gson.annotations.SerializedName
import retrofit2.Call ;
import retrofit2.http.*


 data class SaveMap (
    val id  : Int,
    val name : String,
    val nameAdress : String,
    val latitudeNew : Float,
    val longitudeNew : Float,
    val latitudeOld : Float,
    val longitudeOld : Float

)

public interface Laravel {

    @GET("saved_clients")
    fun getMap():Call<List<SaveMap>>

    /*@GET("/saved_clients/{id}")
    fun getMovieById(@Path("id") id :Int): Call<List<SaveMap>>*/

    @POST("saved_clients")
    fun posMap(
        @Header("name") name: String,
        @Header("nameAdress") nameAdress: String,
        @Header("latitudeNew") latitudeNew: Int,
        @Header("longitudeNew") longitudeNew: Int,
        @Header("latitudeOld") latitudeOld: Int,
        @Header("longitudeOld") longitudeOld: Int
    ) :Call<List<SaveMap>>

}



