package dog.snow.androidrecruittest.repository.service.network

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

interface NetworkDataSource {
    val downloadedPhotoData: LiveData<List<RawPhoto>>
    val downloadedAlbumData: LiveData<List<RawAlbum>>
    val downloadedUserData: LiveData<List<RawUser>>

    suspend fun fetchData(photoLimit: Int ?= 100)
}