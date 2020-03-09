package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

object DataCacheController {

    var rawPhotos : List<RawPhoto>?= null
    var rawAlbums : List<RawAlbum> ?= null
    var rawUsers : List<RawUser> ?= null

}