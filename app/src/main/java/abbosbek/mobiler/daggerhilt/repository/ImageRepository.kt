package abbosbek.mobiler.daggerhilt.repository

import abbosbek.mobiler.daggerhilt.api.ImageService
import javax.inject.Inject

class ImageRepository
@Inject
constructor(private val api : ImageService)
{
    suspend fun getAllImages() = api.getAllImages()
}