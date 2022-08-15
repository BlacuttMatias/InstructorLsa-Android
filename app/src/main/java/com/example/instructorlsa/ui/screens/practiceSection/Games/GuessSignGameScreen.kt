package com.example.instructorlsa.ui.screens.practiceSection.Games

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.buttons.OptionButton
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.BackNavigateButton
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.NextNavigateButton
import com.example.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.example.instructorlsa.viewmodels.games.GuessSignScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignLearningScreenViewModel
import org.intellij.lang.annotations.JdkConstants

@Composable
fun GuessSignGameScreen(screenViewModel: GuessSignScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(30.dp))
        TitleText(text = titleText)
        Spacer(modifier = Modifier.height(30.dp))
        VideoPlayer(urlVideo = screenViewModel.game.sign.urlVideo)
        Spacer(modifier = Modifier.height(30.dp))
        screenViewModel.game.answerOptions.forEach { answerOption ->
            OptionButton(text = answerOption.text) {

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        SloganFooterText(30.dp)
    }
}