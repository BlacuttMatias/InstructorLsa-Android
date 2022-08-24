package com.example.instructorlsa.ui.screens.practiceSection.Games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.buttons.OptionButton
import com.example.instructorlsa.ui.common.components.textFields.GameTextField
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogResultGame
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AnimatedStateResultIcon
import com.example.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.WriteTheSignScreenViewModel

@Composable
fun WriteTheSignGameScreen(screenViewModel: WriteTheSignScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name

    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(30.dp))
        TitleText(text = titleText)
        Spacer(modifier = Modifier.height(30.dp))
        VideoPlayer(urlVideo = screenViewModel.game.sign.urlVideo, playWhenReady = true)
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedStateResultIcon(isVisible = screenViewModel.gameWasCompleted,
            isPositiveResult = (screenViewModel.gameAnsweredCorrectly())
        )
        if(screenViewModel.gameWasCompleted){
            Spacer(modifier = Modifier.height(20.dp))
        }
        else{
            Spacer(modifier = Modifier.height(90.dp))
        }

        GameTextField(initText = "", delegate = screenViewModel)
        Spacer(modifier = Modifier.height(30.dp))

        MainButton(text = "Confirmar") {
            screenViewModel.didTapConfirmButton()
        }

        AlertDialogResultGame(
            isVisble = screenViewModel.showContinueView,
            title = screenViewModel.correctStateText(),
            onClickContinueButton = {
                screenViewModel.didTapContinueButton()
            }
        )
    }
}