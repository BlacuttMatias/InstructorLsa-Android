package com.lsadomain.instructorlsa.ui.screens.practiceSection.Games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lsadomain.instructorlsa.ui.common.components.MainButton
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.extensions.noRippleClickable
import com.lsadomain.instructorlsa.ui.common.components.loadingScreen.OverlapFullScreenLoader
import com.lsadomain.instructorlsa.ui.common.components.textFields.GameTextField
import com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogWithCancelAndConfirmButtons
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogResultGame
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.AnimatedStateResultIcon
import com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components.CountDownTimer
import com.lsadomain.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.WriteTheSignScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WriteTheSignGameScreen(screenViewModel: WriteTheSignScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.getTitleText()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OverlapFullScreenLoader(showLoader = screenViewModel.videoLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .noRippleClickable {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ){
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
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedStateResultIcon(isVisible = screenViewModel.gameWasCompleted,
                isPositiveResult = (screenViewModel.gameAnsweredCorrectly())
            )
            if(screenViewModel.gameWasCompleted){
                Spacer(modifier = Modifier.height(20.dp))
            }
            else{
                Spacer(modifier = Modifier.height(80.dp))
            }

            GameTextField(delegate = screenViewModel)
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