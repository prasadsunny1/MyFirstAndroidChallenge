package com.example.myfirstandroidchallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This class is used to define the application class
 * It is registered as hilt android app
 */
@HiltAndroidApp
internal class MyApplication : Application()