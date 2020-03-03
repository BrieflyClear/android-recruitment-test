package dog.snow.androidrecruittest

import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import io.reactivex.Observable

object DataCacheController {

    var rawPhotos : Observable<List<RawPhoto>> ?= null
    var rawAlbums : List<Observable<RawAlbum>> ?= null
    var rawUsers : List<Observable<RawUser>> ?= null


}