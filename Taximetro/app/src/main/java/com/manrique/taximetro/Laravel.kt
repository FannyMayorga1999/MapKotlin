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
        @Field("name") name: String,
        @Field("nameAdress") nameAdress: String,
        @Field("latitudeNew") latitudeNew: Int,
        @Field("longitudeNew") longitudeNew: Int,
        @Field("latitudeOld") latitudeOld: Int,
        @Field("longitudeOld") longitudeOld: Int
    ) :Call<List<SaveMap>>

}



