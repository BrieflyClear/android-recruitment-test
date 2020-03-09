package dog.snow.androidrecruittest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.repository.service.UserService
import dog.snow.androidrecruittest.repository.service.network.ConnectivityInterceptorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val service = UserService(ConnectivityInterceptorImpl(applicationContext))
        val user = GlobalScope.launch { val repo = service.getUserAsync(1).await()
        println(repo.toString()) }
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