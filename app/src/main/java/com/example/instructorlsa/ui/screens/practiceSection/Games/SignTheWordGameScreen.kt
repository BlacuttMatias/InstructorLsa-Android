package com.example.instructorlsa.ui.screens.practiceSection.Games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.viewmodels.games.GuessSignScreenViewModel
import com.example.instructorlsa.viewmodels.games.signTheWord.SignTheWordGameViewModel

@Composable
fun SignTheWordGameScreen(screenViewModel: SignTheWordGameViewModel, navController: NavController) {
    val titleText = screenViewModel.game.name

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = titleText)
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = screenViewModel.game.sign.name)
        Spacer(modifier = Modifier.height(20.dp))
        MainButton(text = screenViewModel.getMainButtonText()) {
            
        }
    }
}