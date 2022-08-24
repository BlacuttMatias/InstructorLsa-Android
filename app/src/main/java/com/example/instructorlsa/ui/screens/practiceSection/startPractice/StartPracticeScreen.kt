package com.example.instructorlsa.ui.screens.practiceSection.startPractice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.FooterSloganAndIcon
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.viewmodels.signs.SignsScreenViewModel
import com.example.instructorlsa.viewmodels.startPractice.StartPracticeViewModel

@Composable
fun StartPracticeScreen(navController: NavController, screenViewModel: StartPracticeViewModel) {
    val titleText = screenViewModel.category.name
    val startButtonText = stringResource(id = R.string.start_to_play)
    val icon = painterResource(id = R.drawable.ic_icon_app)
    val titleTopTabBarText = stringResource(id = R.string.home_practice_section)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(70.dp))
            MainButton(text = startButtonText) {
                navController.navigate(NavigationRoute.GamePractice.route)
            }
            FooterSloganAndIcon(icon = icon)
        }
    }
}