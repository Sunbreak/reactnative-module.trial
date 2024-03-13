package com.example.myandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import com.swmansion.reanimated.ReanimatedPackage

class MyReactActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
    private lateinit var reactRootView: ReactRootView
    private lateinit var reactInstanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        SoLoader.init(this, false)
        reactRootView = ReactRootView(this)

        val reanimatedPackage = object : ReanimatedPackage() {
            override fun getReactInstanceManager(reactContext: ReactApplicationContext?): ReactInstanceManager {
                return reactInstanceManager
            }
        }
        val packages = PackageList(application).packages.also { it ->
            it.replaceAll { p ->
                if (p is ReanimatedPackage) reanimatedPackage else p
            }
        }
        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(MyReactNativePackage())
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
        reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        reactRootView.startReactApplication(reactInstanceManager, "MyReactNativeApp", null)
        setContentView(reactRootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        reactInstanceManager.destroy()
        reactRootView.unmountReactApplication()
    }

    override fun onPause() {
        super.onPause()
        reactInstanceManager.onHostPause(this)
    }

    override fun onResume() {
        super.onResume()
        reactInstanceManager.onHostResume(this)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onBackPressed() {
        reactInstanceManager.onBackPressed()
        super.onBackPressed()
    }
}