package edu.uw.mistyb01.recipefinder.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.edamam.com/api/"

// The API Interface
interface RecipeSearchNetworkService {
    // endpoints
    @GET("recipes/v2")
    fun getRecipeSearchResults(
        @Query("type") type: String,
        @Query("q") q: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String): Call<String>

    @GET("recipes/v2/{id}")
    fun getRecipeDetails(
        @Path("id") id: String,
        @Query("public") type: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String): Call<String>

}

// retrofit factory:
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


object RecipeSearchAPI{
    val retrofitService: RecipeSearchNetworkService by lazy {
        retrofit.create(RecipeSearchNetworkService::class.java)
    }

}

/*

data class Recipe(
    @Json(name = "uri")
    val uri: String,

    @Json(name = "label")
    val recipeName: String

)

data class RecipeResult (
    val results: List<Recipe>
    )


 */