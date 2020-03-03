package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import retrofit2.Call

object DataCacheController {

    var rawPhotos : Call<List<RawPhoto>>?= null
    var rawAlbums : List<Call<RawAlbum>> ?= null
    var rawUsers : List<Call<RawUser>> ?= null

}