package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.spacexdata.com/v3/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

 interface SpaceXApiService {
    @GET("launches")
    suspend fun getProperties():List<SpaceXProperty>
}

 object SpaceXApi{
    val retrofitservice :SpaceXApiService by lazy {
    retrofit.create(SpaceXApiService::class.java)
    }
}