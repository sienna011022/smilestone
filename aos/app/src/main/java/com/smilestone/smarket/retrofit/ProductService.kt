package com.smilestone.smarket.retrofit

import com.smilestone.smarket.dto.ChangeProduct
import com.smilestone.smarket.dto.EditData
import com.smilestone.smarket.dto.Product
import retrofit2.Call
import retrofit2.http.*
import java.nio.channels.AsynchronousChannelGroup

interface ProductService {

    @GET("/api/product/list/all")
    fun requestProducts(
        @Header("Authorization") token: String
    ): Call<ArrayList<Product>>

    @GET("/api/product/title")
    fun requestSearch(
        @Header("Authorization") token: String,
        @Query("title") title : String
    ): Call<ArrayList<Product>>


    @POST("/api/product/post")
    fun uploadProduct(
        @Header("Authorization") token: String,
        @Body() params: EditData
    ):Call<Product>

    @GET("/api/product/id")
    fun getItem(
        @Header("Authorization") token: String,
        @Query("productId") productId: Long
    ) : Call<Product>

    @GET("/api/product/seller/all")
    fun getSellList(
        @Header("Authorization") token: String,
        @Query("sellerId") userId: Long
    ) : Call<ArrayList<Product>>

    @GET("/api/product/buyer/all")
    fun getPurchaseList(
        @Header("Authorization") token: String,
        @Query("buyerId") userId: Long
    ): Call<ArrayList<Product>>

    @GET("/api/product/delete")
    fun deleteProduct(
        @Header("Authorization") token: String,
        @Query("productId") id: Long
    ) : Call<Long>

    @POST("/api/product/update")
    fun changeProduct(
        @Header("Authorization") token: String,
        @Body() product : ChangeProduct
    ): Call<Long>

    @GET("/api/product/category")
    fun getProduct(
        @Header("Authorization") token:String,
        @Query("category") category: String
    ) : Call<ArrayList<Product>>
}