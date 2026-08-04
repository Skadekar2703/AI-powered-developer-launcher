package com.devlaunch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devlaunch.android.navigation.AppNavGraph
import com.devlaunch.android.ui.theme.DevLaunchAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DevLaunchAndroidTheme {

                AppNavGraph()

            }

        }
    }

}