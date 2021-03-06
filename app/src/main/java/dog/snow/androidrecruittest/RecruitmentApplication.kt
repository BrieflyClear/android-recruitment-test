package dog.snow.androidrecruittest

import android.app.Application
import dog.snow.androidrecruittest.repository.network.service.AlbumService
import dog.snow.androidrecruittest.repository.network.service.PhotoService
import dog.snow.androidrecruittest.repository.network.service.UserService
import dog.snow.androidrecruittest.repository.network.ConnectivityInterceptor
import dog.snow.androidrecruittest.repository.network.ConnectivityInterceptorImpl
import dog.snow.androidrecruittest.repository.network.NetworkDataSource
import dog.snow.androidrecruittest.repository.network.NetworkDataSourceImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class RecruitmentApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@RecruitmentApplication))
        /*
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { PhotoService.invoke(instance()) }
        bind() from singleton { UserService.invoke(instance()) }
        bind() from singleton { AlbumService.invoke(instance()) }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance(), instance(), instance()) }*/
    }

    override fun onCreate() {
        super.onCreate()
        //AndroidThreeTen.init(this)
    }
}