package com.example.instructorlsa.ui.screens.practiceSection.Games

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavController
import com.example.instructorlsa.constants.Constants
import com.example.instructorlsa.ui.common.components.BodyText
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogBack
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogResultGame
import com.example.instructorlsa.viewmodels.games.signTheWord.SignTheWordGameViewModel


@Composable
fun SignTheWordGameScreen(screenViewModel: SignTheWordGameViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name
    val context = LocalContext.current
    var launcherVideoCapture = rememberLauncherForActivityResult(CaptureVideoContract()){
        screenViewModel.checkIfAnswerIsCorrect()
    }

    var launcherPermissions = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        screenViewModel.permissionsWasRequested(context)
    }

    if (screenViewModel.loading) {
        FullScreenLoader()
    }
    else{
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(30.dp))
            TitleText(text = screenViewModel.game.sign.name)
            Spacer(modifier = Modifier.height(40.dp))
            BodyText(text = screenViewModel.getBodyText())
            Spacer(modifier = Modifier.height(50.dp))
            MainButton(text = screenViewModel.getMainButtonText()) {
                if(screenViewModel.hasPermission){
                    launcherVideoCapture.launch(screenViewModel.getUriFile(context))
                }
                else{
                    launcherPermissions.launch(
                        arrayOf(
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                        )
                    )
                }
            }
            AlertDialogResultGame(
                isVisble = screenViewModel.showContinueView,
                title = screenViewModel.correctStateText(),
                onClickContinueButton = {
                    screenViewModel.didTapContinueButton()
                }
            )
            BackHandler(true) {
                screenViewModel.delegate.shouldShowBackAlertDialog = true
            }
            AlertDialogBack(
                isVisible = screenViewModel.delegate.shouldShowBackAlertDialog,
                title = screenViewModel.delegate.getDialogBodyText(),
                onClickConfirmButton = { navController.navigateUp() },
                onClickCancelButton = { screenViewModel.delegate.onAlertDialogCancelButtonPressed() },
                onDismissRequest = {  screenViewModel.delegate.onAlertDialogCancelButtonPressed() }
            )
            AlertDialogBack(
                isVisible = screenViewModel.shouldShowNotPermissionsGrantedView,
                title = screenViewModel.getNotPermissionsGrantedViewBodyText(),
                confirmButtonText = screenViewModel.getNotPermissionsGrantedViewConfirmButtonText(),
                onClickConfirmButton = { screenViewModel.onClickConfirmButtonNotPermissionsGrantedDialog() },
                onClickCancelButton = { screenViewModel.onClickCancelButtonNotPermissionsGrantedDialog() },
                onDismissRequest = {  screenViewModel.onDismissRequestNotPermissionsGrantedDialog() }
            )
            if(screenViewModel.mustShowVideoCapture){
                launcherVideoCapture.launch(screenViewModel.getUriFile(context))
                screenViewModel.mustShowVideoCapture = false
//            val builder = VmPolicy.Builder()
//            StrictMode.setVmPolicy(builder.build())
//            Log.d("ASDASDASDASD", file.toString() + "  " + file.toURI().toString())
//            val file = File(context.cacheDir,"videoexample.mp4")
//            if (!file.exists()) {
//                file.createNewFile()
//            }

//            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStorageDirectory().path +"videocapture_example.mp4")
//            startActivity(context, intent, null)
            }
        }
    }
}

class CaptureVideoContract : ActivityResultContract<Uri,  Uri?>() {
    @CallSuper
    override fun createIntent(context: Context, input: Uri): Intent {
        return Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            .putExtra(MediaStore.EXTRA_OUTPUT, input)
            .putExtra(MediaStore.EXTRA_DURATION_LIMIT, Constants.secondsToTakeVideo)
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