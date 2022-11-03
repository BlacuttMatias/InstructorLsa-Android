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
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.BuildConfig
import com.example.instructorlsa.mappers.ResultCheckVideoMapper
import com.example.instructorlsa.services.SignVideoService
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel
import kotlinx.coroutines.launch
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
    var showContinueView by mutableStateOf(false)
    var loading by mutableStateOf(false)
    var signVideoService: SignVideoService
    var videoFile: File = File("")
    var gameCompletedCorrectly = false
    var shouldShowNotPermissionsGrantedView by mutableStateOf(false)
    var shouldShowRetryView by mutableStateOf(false)
    var resultResponseCheckVideoMapper: ResultCheckVideoMapper

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
        this.signVideoService = SignVideoService()
        this.resultResponseCheckVideoMapper = ResultCheckVideoMapper()
    }

    fun permissionsWasRequested(context: Context) {
         hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        mustShowVideoCapture = hasPermission
        shouldShowNotPermissionsGrantedView = !hasPermission
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
        viewModelScope.launch {
            loading = true
            try{
                val response = signVideoService.checkSignVideo(idSign = game.sign.id.toString(),
                    position = game.position.toString(),
                    category = game.category ?: "",
                    videoFile = videoFile)
                if (response.isSuccessful) {
                    Log.d("9999999999999999999999", response.body().toString())
                    val result = resultResponseCheckVideoMapper.map(response.body())
                    if(result.hasToRetry()){
                        shouldShowRetryView = true
                    }
                    else{
                        gameCompletedCorrectly = result.isCorrect()
                        showContinueView = true
                    }
                }
                else{
                    delegate.showError()
                }
            }
            catch(e: Exception){
                delegate.showError()
            }
            finally {
                loading = false
            }
        }
    }

    fun getMainButtonText(): String{
        return "Empezar a grabar"
    }

    fun correctStateText(): String{
        return if(gameCompletedCorrectly){
            "¡Correcto!"
        } else{
            "¡Incorrecto!"
        }
    }

    fun didTapContinueButton(){
        delegate.goToNextScreen(gameCompletedCorrectly)
        showContinueView = false
    }

    fun getBodyText(): String {
        return "A continuación deberás grabarte realizando la seña de la palabra \"" + game.sign.name + "\""
    }

    fun onDismissRequestNotPermissionsGrantedDialog(){
        shouldShowNotPermissionsGrantedView = false
    }

    fun getNotPermissionsGrantedViewBodyText(): String{
        return "Si no otorgas permiso para acceder a la cámara, se dará por incorrecta tu respuesta. ¿Deseas continuar de todas formas?"
    }

    fun getNotPermissionsGrantedViewConfirmButtonText(): String{
        return "Continuar"
    }

    fun onClickCancelButtonNotPermissionsGrantedDialog(){
        onDismissRequestNotPermissionsGrantedDialog()
    }

    fun onClickConfirmButtonNotPermissionsGrantedDialog(){
        shouldShowNotPermissionsGrantedView = false
        showContinueView = true
    }

    fun getBodyRetryView(): String{
        return "No se detectaron sus manos y cara correctamente. Desea volver a intertarlo o saltear el juego?\nSi saltea el juego se dará por incorrecta su respuesta."
    }

    fun getPrimaryButtonTextRetryView(): String{
        return "Reintentar"
    }

    fun getSecondaryButtonTextRetryView(): String{
        return "Saltear juego"
    }

    fun onClickPrimaryButtonRetryView(){
        shouldShowRetryView = false
    }

    fun onClickSecondaryButtonRetryView(){
        shouldShowRetryView = false
        gameCompletedCorrectly = false
        showContinueView = true
    }

    fun getSecondaryButtonText(): String{
        return "Saltear juego"
    }

    fun onClickSecondaryButton(){
        gameCompletedCorrectly = false
        showContinueView = true
    }
}