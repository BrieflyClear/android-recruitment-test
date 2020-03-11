package dog.snow.androidrecruittest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.network.service.UserService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


class SplashActivity : AppCompatActivity(R.layout.splash_activity) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        /*val ci = ConnectivityInterceptorImpl(this.applicationContext)
        val phs = PhotoService.invoke(ci)
        val als = AlbumService.invoke(ci)
        val uss = UserService.invoke(ci)
        val dataSource = NetworkDataSourceImpl(phs, als, uss)*/
        /*val data = GlobalScope.async {
        }*/
        /*GlobalScope.async(Dispatchers.Default) {
            dataSource.fetchData()
        }*/

        val apiService = UserService()
        val call: Observable<RawUser> = apiService.getUser(1)
        val disposable: Any = call
            .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : Disposable<RawUser>(),
                Observer<RawUser> {
                fun onCompleted() {
                    Log.e("fd", "dd")
                }
                fun onError(e: Throwable) {
                    // cast to retrofit.HttpException to get the response code
                    if (e is HttpException) {
                        val response: HttpException = e as HttpException
                        val code: Int = response.code()
                    }
                }

                fun onNext(user: RawUser?) {}
            })


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }
}