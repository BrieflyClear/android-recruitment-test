package dog.snow.androidrecruittest.repository.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dog.snow.androidrecruittest.repository.DataCacheController
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.network.service.AlbumService
import dog.snow.androidrecruittest.repository.network.service.PhotoService
import dog.snow.androidrecruittest.repository.network.service.UserService

class NetworkDataSourceImpl(
    private val photoService: PhotoService, private val albumService: AlbumService, private val userService: UserService)
    : NetworkDataSource {

    private val _downloadedPhotoData = MutableLiveData<List<RawPhoto>>()
    override val downloadedPhotoData: LiveData<List<RawPhoto>>
        get() = _downloadedPhotoData
    private val _downloadedAlbumData = MutableLiveData<List<RawAlbum>>()
    override val downloadedAlbumData: LiveData<List<RawAlbum>>
        get() = _downloadedAlbumData
    private val _downloadedUserData = MutableLiveData<List<RawUser>>()
    override val downloadedUserData: LiveData<List<RawUser>>
        get() =  _downloadedUserData

    override suspend fun fetchData(photoLimit: Int?) {
        /*val fetchedPhotos = photoService.getPhotosAsync(photoLimit).await()
        _downloadedPhotoData.postValue(fetchedPhotos)
        val albumIds = mutableListOf<Int>()
        fetchedPhotos.forEach{if(!albumIds.contains(it.albumId)) albumIds.add(it.albumId)}

        val fetchedAlbums = mutableListOf<RawAlbum>()
        for(it : Int in albumIds) {
            fetchedAlbums.add(albumService.getAlbumAsync(it).await())
        }
        _downloadedAlbumData.postValue(fetchedAlbums)
        val usersIds = mutableListOf<Int>()
        fetchedAlbums.forEach{if(!usersIds.contains(it.userId)) albumIds.add(it.userId)}
        val fetchedUsers = mutableListOf<RawUser>()
        for(it : Int in albumIds) {
            fetchedUsers.add(userService.getUserAsync(it).await())
        }
        _downloadedUserData.postValue(fetchedUsers)

        DataCacheController.rawAlbums = _downloadedAlbumData.value
        DataCacheController.rawUsers = _downloadedUserData.value
        DataCacheController.rawPhotos = _downloadedPhotoData.value*/
    }
}