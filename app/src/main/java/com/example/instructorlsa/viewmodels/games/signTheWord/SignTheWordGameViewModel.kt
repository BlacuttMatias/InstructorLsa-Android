package com.example.instructorlsa.viewmodels.games.signTheWord

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.instructorlsa.BuildConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel
import java.io.File

class SignTheWordGameViewModel(
    game: GameViewModel,
    category: CategoryViewModel,
    delegate: GameScreenViewModel
): ViewModel() {
    val game: GameViewModel
    val category: CategoryViewModel
    var delegate: GameScreenViewModel
    var hasPermission by mutableStateOf(false)
    var mustShowVideoCapture by mutableStateOf(false)
    var videoFile: File = File("")

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
    }

    fun permissionsWasRequested(context: Context) {
         hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        mustShowVideoCapture = hasPermission
    }

    fun getUriFile(context: Context): Uri{
        videoFile = File(context.filesDir,"example1.mp4")
        val uri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider",
            videoFile
        )
        Log.d("22222222222222222222", videoFile.length().toString())
        if (!videoFile.exists()) {
            videoFile.createNewFile()
        }
        Log.d("11111111111111111", uri.toString())
        return uri
    }

    fun checkIfAnswerIsCorrect() {

    }

    fun getMainButtonText(): String{
        return "Empezar a grabar"
    }
}