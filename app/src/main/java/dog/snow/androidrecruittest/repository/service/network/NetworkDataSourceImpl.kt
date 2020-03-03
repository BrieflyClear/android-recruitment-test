package dog.snow.androidrecruittest.repository.service.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.service.AlbumService
import dog.snow.androidrecruittest.repository.service.PhotoService
import dog.snow.androidrecruittest.repository.service.UserService

class NetworkDataSourceImpl(
    private val photoService: PhotoService, private val albumService: AlbumService, userService: UserService)
    : NetworkDataSource {

    private val _downloadedPhotoData = MutableLiveData<RawPhoto>()
    override val downloadedPhotoData: LiveData<RawPhoto>
        get() = _downloadedPhotoData
    private val _downloadedAlbumData = MutableLiveData<RawAlbum>()
    override val downloadedAlbumData: LiveData<RawAlbum>
        get() = _downloadedAlbumData
    private val _downloadedUserData = MutableLiveData<RawUser>()
    override val downloadedUserData: LiveData<RawUser>
        get() =  _downloadedUserData

    override suspend fun fetchData(photoLimit: Int?) {
        val fetchedPhotos = photoService.getPhotosAsync(photoLimit).await()
        _downloadedPhotoData.postValue(fetchedPhotos[1]) //TODO change to a list
        // TODO add albums and users
        // TODO add throwing NoConncetivityException and catch it in the SplashScreen
    }
}