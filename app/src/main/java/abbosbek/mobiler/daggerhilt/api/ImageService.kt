package abbosbek.mobiler.daggerhilt.api

import abbosbek.mobiler.daggerhilt.models.ImageItem
import abbosbek.mobiler.daggerhilt.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID ${Constants.CLIENT_ID}")
    @GET(Constants.END_POINT)
    suspend fun getAllImages() : Response<List<ImageItem>>

}