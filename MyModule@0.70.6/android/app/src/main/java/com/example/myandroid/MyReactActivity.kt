package com.example.myandroid

import android.app.Activity
import android.os.Bundle
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.*
import com.facebook.react.common.LifecycleState
import com.facebook.react.fabric.ComponentFactory
import com.facebook.react.fabric.CoreComponentsRegistry
import com.facebook.react.fabric.FabricJSIModuleProvider
import com.facebook.react.fabric.ReactNativeConfig
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage
import com.facebook.react.uimanager.ViewManagerRegistry
import com.facebook.soloader.SoLoader

class MyReactActivity : Activity(), DefaultHardwareBackBtnHandler {
    private lateinit var reactRootView: ReactRootView
    private lateinit var reactInstanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)
        reactRootView = ReactRootView(this)
        reactRootView.setIsFabric(true)
        val packages: List<ReactPackage> = listOf(MainReactPackage(null))
        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(MyReactNativePackage())
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
        val jsiModulePackage = object : JSIModulePackage {
            lateinit var reactInstanceManager_: ReactInstanceManager

            override fun getJSIModules(
                reactApplicationContext: ReactApplicationContext,
                jsContext: JavaScriptContextHolder
            ): MutableList<JSIModuleSpec<out JSIModule>> {
                return mutableListOf(object : JSIModuleSpec<UIManager> {
                    override fun getJSIModuleType() = JSIModuleType.UIManager

                    override fun getJSIModuleProvider(): JSIModuleProvider<UIManager> {
                        val componentFactory = ComponentFactory()
                        CoreComponentsRegistry.register(componentFactory)
                        val viewManagerRegistry = ViewManagerRegistry(
                            reactInstanceManager_.getOrCreateViewManagers(reactApplicationContext)
                        )
                        return FabricJSIModuleProvider(
                            reactApplicationContext,
                            componentFactory,
                            ReactNativeConfig.DEFAULT_CONFIG,
                            viewManagerRegistry,
                        )
                    }
                })
            }
        }

        reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .setJSIModulesPackage(jsiModulePackage)
            .setJsEngineAsHermes(true)
            .build()

        jsiModulePackage.reactInstanceManager_ = reactInstanceManager
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        reactRootView.startReactApplication(reactInstanceManager, "MyReactNativeApp", null)
        setContentView(reactRootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        reactInstanceManager.onHostDestroy(this)
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