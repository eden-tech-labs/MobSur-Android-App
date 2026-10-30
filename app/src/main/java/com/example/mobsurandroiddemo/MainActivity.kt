package com.example.mobsurandroiddemo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import io.edentechlabs.survey.sdk.MobSur
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Light status bar icons on the colored toolbar, in light and dark mode.
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyWindowInsets()

        // The SDK shows surveys in a bottom sheet through this FragmentManager (held weakly).
        MobSur.setFragmentManager(supportFragmentManager)

        findViewById<TextView>(R.id.sdkVersion).text = getString(R.string.sdk_version, MobSur.VERSION)
        findViewById<Button>(R.id.button).setOnClickListener {
            MobSur.event("MY_Event")
        }

        if (BuildConfig.DEBUG) {
            findViewById<View>(R.id.testUserPanel).visibility = View.VISIBLE
            findViewById<Button>(R.id.newTestUser).setOnClickListener { newTestUser(it) }
        }
    }

    private fun applyWindowInsets() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { root, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            toolbar.updatePadding(top = bars.top)
            root.updatePadding(left = bars.left, right = bars.right, bottom = bars.bottom)
            insets
        }
    }

    private fun newTestUser(anchor: View) {
        val userId = UUID.randomUUID().toString()
        TestUser.set(this, userId)
        MobSur.updateUserId(userId)
        Snackbar.make(anchor, R.string.test_user_created, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.restart) { restartApp() }
            .show()
    }

    // The SDK shows at most one survey per process, so start a new one.
    private fun restartApp() {
        val intent = packageManager.getLaunchIntentForPackage(packageName) ?: return
        startActivity(Intent.makeRestartActivityTask(intent.component))
        Runtime.getRuntime().exit(0)
    }
}
