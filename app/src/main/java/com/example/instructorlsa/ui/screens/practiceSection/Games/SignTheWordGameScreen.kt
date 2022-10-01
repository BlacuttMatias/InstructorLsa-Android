package com.example.instructorlsa.ui.screens.practiceSection.Games

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.os.FileUtils
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.instructorlsa.BuildConfig
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.viewmodels.games.signTheWord.SignTheWordGameViewModel
import java.io.File


@Composable
fun SignTheWordGameScreen(screenViewModel: SignTheWordGameViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name
    val context = LocalContext.current
    var launcher = rememberLauncherForActivityResult(CaptureVideoContract()){
        Log.d("ASDASDASDSADASD", it.toString())
    }

    var launcherPermissions = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        val hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        screenViewModel.hasPermission = hasPermission
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = titleText)
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = screenViewModel.game.sign.name)
        Spacer(modifier = Modifier.height(20.dp))
        MainButton(text = screenViewModel.getMainButtonText()) {
            launcherPermissions.launch(
                arrayOf(
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            )
        }
        if(screenViewModel.hasPermission){
            val file = File(context.filesDir,"example1.mp4")
            val uri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider",
                file
            )
            Log.d("22222222222222222222", file.length().toString())
            if (!file.exists()) {

                file.createNewFile()
            }
//            val builder = VmPolicy.Builder()
//            StrictMode.setVmPolicy(builder.build())
//            Log.d("ASDASDASDASD", file.toString() + "  " + file.toURI().toString())
//            val file = File(context.cacheDir,"videoexample.mp4")
//            if (!file.exists()) {
//                file.createNewFile()
//            }
            Log.d("11111111111111111", uri.toString())
            launcher.launch(uri)
//            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStorageDirectory().path +"videocapture_example.mp4")
//            startActivity(context, intent, null)
        }
    }
}

class CaptureVideoContract : ActivityResultContract<Uri,  Uri?>() {
    @CallSuper
    override fun createIntent(context: Context, input: Uri): Intent {
        return Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            .putExtra(MediaStore.EXTRA_OUTPUT, input)
            .putExtra(MediaStore.EXTRA_DURATION_LIMIT, 6)
            .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    final override fun getSynchronousResult(
        context: Context,
        input: Uri
    ): SynchronousResult< Uri?>? = null

    @Suppress("AutoBoxing")
    final override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}