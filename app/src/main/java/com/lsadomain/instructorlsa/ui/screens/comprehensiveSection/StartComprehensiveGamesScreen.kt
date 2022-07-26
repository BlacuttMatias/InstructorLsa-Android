package com.lsadomain.instructorlsa.ui.screens.comprehensiveSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.ui.common.components.BodyText
import com.lsadomain.instructorlsa.ui.common.components.MainButton
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.alerts.AlertDialogInfo
import com.lsadomain.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.comprehensiveGamesSection.StartComprehensiveGamesScreenViewModel

@Composable
fun StartComprehensiveGamesScreen(navController: NavController, screenViewModel: StartComprehensiveGamesScreenViewModel) {
    val titleText = stringResource(id = R.string.home_comprehensive_games)
    val startButtonText = stringResource(id = R.string.start_to_play)
    val titleTopTabBarText = stringResource(id = R.string.app_name)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText,
            navController = navController,
            showInfoButton = screenViewModel.shouldShowInfoButton(),
            onInfoButtonPressed = { screenViewModel.onClickInfoInfoIconButton() } )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(50.dp))
            MainButton(text = startButtonText) {
                InstructorLsaConfig.restartValueGames()
                navController.navigate(NavigationRoute.GamePractice.route)
            }
            Spacer(modifier = Modifier.height(80.dp))
            BodyText(text = screenViewModel.getBodyIdText(), fontSize = 15.sp)
            Spacer(modifier = Modifier.height(20.dp))
            screenViewModel.getCategoriesNamesToShow().forEach {
                BodyText(text = it, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(2.dp))
            }
            AlertDialogInfo(
                isVisible = screenViewModel.shouldShowInfoAlert,
                title = screenViewModel.getBodyTextAlertInfo(),
                primaryButtonText = screenViewModel.getTextAlertInfoPrimaryButton(),
                onDismissRequest = {},
                onClickContinueButton = { screenViewModel.onClickAlertInfoPrimaryButton() }
            )
        }
    }
}