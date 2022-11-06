package com.lsadomain.instructorlsa.ui.screens.practiceSection.Games

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.buttons.OptionButton
import com.lsadomain.instructorlsa.ui.common.components.loadingScreen.OverlapFullScreenLoader
import com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogWithCancelAndConfirmButtons
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogResultGame
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AnimatedStateResultIcon
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.CountDownTimer
import com.lsadomain.instructorlsa.viewmodels.games.GuessSignScreenViewModel

@Composable
fun GuessSignGameScreen(screenViewModel: GuessSignScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name

    OverlapFullScreenLoader(showLoader = screenViewModel.videoLoading) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(0.8f))
                TitleText(text = titleText, textAlign = TextAlign.Center)
                Column(
                    modifier = Modifier.weight(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CountDownTimer(viewModel = screenViewModel.countDownViewModel)
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            VideoPlayer(urlVideo = screenViewModel.game.sign.urlVideo, playWhenReady = true, delegate = screenViewModel)
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedStateResultIcon(isVisible = screenViewModel.answerWasSelected,
                isPositiveResult = (screenViewModel.answerSelected?.isCorrect ?: false)
            )
            if(screenViewModel.answerWasSelected){
                Spacer(modifier = Modifier.height(10.dp))
            }
            else{
                Spacer(modifier = Modifier.height(70.dp))
            }

            screenViewModel.game.answerOptions.forEach { answerOption ->
                OptionButton(text = answerOption.text, showState = screenViewModel.showStateAnswer(answerOption),isCorrect = answerOption.isCorrect) {
                    screenViewModel.didTapAnswerOption(answerOption)
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            AlertDialogResultGame(
                isVisble = screenViewModel.showContinueView,
                title = screenViewModel.correctStateText(),
                onClickContinueButton = {
                    screenViewModel.didTapContinueButton()
                }
            )
            AlertDialogWithCancelAndConfirmButtons(
                isVisible = screenViewModel.delegate.shouldShowBackAlertDialog,
                title = screenViewModel.delegate.getDialogBodyText(),
                onClickConfirmButton = { navController.navigateUp() },
                onClickCancelButton = { screenViewModel.delegate.onAlertDialogCancelButtonPressed() },
                onDismissRequest = {  screenViewModel.delegate.onAlertDialogCancelButtonPressed() }
            )
        }
    }
}