package com.mataku.middleellipsistextsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mataku.middleellipsistextsample.material.MaterialActivity
import com.mataku.middleellipsistextsample.material3.Material3Activity

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface {
          MainScreen(
            navigateToMaterial = {
              startActivity(Intent(this, MaterialActivity::class.java))
            },
            navigateToMaterial3 = {
              startActivity(Intent(this, Material3Activity::class.java))
            }
          )
        }
      }
    }
  }
}
