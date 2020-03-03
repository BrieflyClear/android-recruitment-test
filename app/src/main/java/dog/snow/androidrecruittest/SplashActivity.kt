package dog.snow.androidrecruittest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.service.AlbumService
import dog.snow.androidrecruittest.repository.service.PhotoService
import dog.snow.androidrecruittest.repository.service.UserService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    private var disposable: Disposable ?= null

    private val photoService by lazy {
        PhotoService.create()
    }
    private val albumService by lazy {
        AlbumService.create()
    }
    private val userService by lazy {
        UserService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        beginSearch()
    }

    private fun beginSearch() {
        if(photoService.getPhotos() == null)
            showError("not found")
        /*disposable = userService.getUser(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> print(result.id.toString() +  "result found") },
                { error -> showError(error.message) }
            )*/
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