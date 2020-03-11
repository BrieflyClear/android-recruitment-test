package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

object DataCacheController {

    var rawPhotos : MutableList<RawPhoto> = mutableListOf()
    var rawAlbums : MutableList<RawAlbum> = mutableListOf()
    var rawUsers : MutableList<RawUser> = mutableListOf()
}