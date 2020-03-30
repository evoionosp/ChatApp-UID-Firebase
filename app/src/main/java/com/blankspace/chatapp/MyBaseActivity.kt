package  com.blankspace.chatapp

import android.app.Activity
import android.app.ProgressDialog
import android.nfc.NfcAdapter
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pixplicity.easyprefs.library.Prefs
import ir.drax.netwatch.NetWatch
import ir.drax.netwatch.cb.NetworkChangeReceiver_navigator

abstract class MyBaseActivity : AppCompatActivity() {
    private var mProgressDialog: ProgressDialog? = null

    var isConnected = true


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNetWatcher(this)
    }


    private fun setupNetWatcher(activity: Activity?) {
        NetWatch.builder(activity)
            .setCallBack(object : NetworkChangeReceiver_navigator {
                override fun onConnected(source: Int) {
                    isConnected = true
                    // Toast.makeText(getApplicationContext(), "Internet connected", Toast.LENGTH_LONG).show();
                }

                override fun onDisconnected(): View? { // do some other stuff
                    isConnected = false
                    Toast.makeText(
                        applicationContext,
                        "No internet connection !",
                        Toast.LENGTH_LONG
                    ).show()
                    return null //To display a dialog simply return a custom view or just null to ignore it
                }
            })
            .setNotificationEnabled(false)
            .build()
    }

    override fun onResume() {
        setupNetWatcher(this)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        NetWatch.unregister(this)

    }

    override fun onDestroy() {
        NetWatch.unregister(this)
        super.onDestroy()
    }

    protected fun showProgress(msg: String?) {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) dismissProgress()
        mProgressDialog =
            ProgressDialog.show(this, resources.getString(R.string.app_name), msg)
    }

    protected fun dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    protected fun showToast(str_id: Int) {
        showToast(getString(str_id))
    }

    protected fun showToast(str_id: Int, length: Int) {
        showToast(getString(str_id), length)
    }

    protected fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    protected fun showToast(msg: String?, length: Int) {
        Toast.makeText(this, msg, length).show()
    }

    protected fun showSnack(msg: String?, v: View?) {
        Snackbar.make(v!!, msg!!, Snackbar.LENGTH_LONG).show()
    }

    protected fun showSnack(msg: String?, length: Int, v: View?) {
        Snackbar.make(v!!, msg!!, length).show()
    }

    protected fun showSnack(
        msg: String?,
        length: Int,
        btn: String?,
        onClickListener: View.OnClickListener?,
        v: View?
    ) {
        Snackbar.make(v!!, msg!!, length).setAction(btn, onClickListener).show()
    }
}