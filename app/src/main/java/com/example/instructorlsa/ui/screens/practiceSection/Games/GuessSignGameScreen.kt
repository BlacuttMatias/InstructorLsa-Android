package com.example.instructorlsa.ui.screens.practiceSection.Games

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.buttons.OptionButton
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.BackNavigateButton
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.NextNavigateButton
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AlertDialogResultGame
import com.example.instructorlsa.ui.screens.practiceSection.Games.components.AnimatedStateResultIcon
import com.example.instructorlsa.viewmodels.games.GuessSignScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignLearningScreenViewModel
import org.intellij.lang.annotations.JdkConstants

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GuessSignGameScreen(screenViewModel: GuessSignScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(30.dp))
        TitleText(text = titleText)
        Spacer(modifier = Modifier.height(30.dp))
        VideoPlayer(urlVideo = screenViewModel.game.sign.urlVideo, playWhenReady = true)
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedStateResultIcon(isVisible = screenViewModel.answerWasSelected,
            isPositiveResult = (screenViewModel.answerSelected?.isCorrect ?: false)
        )
        if(screenViewModel.answerWasSelected){
            Spacer(modifier = Modifier.height(20.dp))
        }
        else{
            Spacer(modifier = Modifier.height(90.dp))
        }

        screenViewModel.game.answerOptions.forEach { answerOption ->
            OptionButton(text = answerOption.text, showState = screenViewModel.showStateAnswer(answerOption),isCorrect = answerOption.isCorrect) {
                screenViewModel.didTapAnswerOption(answerOption)
            }
            Spacer(modifier = Modifier.height(20.dp))
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