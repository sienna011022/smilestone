package com.smilestone.smarket.retrofit

import com.smilestone.smarket.dto.UserData
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("/api/users")
    fun getUser(
        @Header("Authorization") token : String,
        @Query("id") id: Long
    ) : Call<UserData>

    @FormUrlEncoded
    @POST("/api/users/info/nickName")
    fun changeNickName(
        @Header("Authorization") token : String,
        @Field("nickName") nickName: String,
        @Field("newNickName") newNickName: String
    ) : Call<UserData>

    @FormUrlEncoded
    @POST("/api/users/info/password")
    fun changePassword(
        @Header("Authorization") token : String,
        @Field("id") id: Long,
        @Field("password") password: String,
        @Field("newPassword") newPassword: String
    ): Call<Long>
}