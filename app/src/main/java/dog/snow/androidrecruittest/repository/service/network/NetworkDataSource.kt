package dog.snow.androidrecruittest.repository.service.network

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

interface NetworkDataSource {
    val downloadedPhotoData: LiveData<RawPhoto>
    val downloadedAlbumData: LiveData<RawAlbum>
    val downloadedUserData: LiveData<RawUser>

    suspend fun fetchData(photoLimit: Int ?= 100)
}