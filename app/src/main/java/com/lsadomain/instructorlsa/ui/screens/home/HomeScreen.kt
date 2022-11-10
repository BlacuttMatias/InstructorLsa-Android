package com.lsadomain.instructorlsa.ui.screens.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.ui.common.components.FooterSloganAndIcon
import com.lsadomain.instructorlsa.ui.common.components.MainButton
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.alerts.AlertDialogInfo
import com.lsadomain.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.lsadomain.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.lsadomain.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.home.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavController, screenViewModel: HomeScreenViewModel) {
    val titleText = stringResource(id = R.string.home_main_menu)
    val learningSectionButtonText = stringResource(id = R.string.home_learning_section)
    val practiceSectionButtonText = stringResource(id = R.string.home_practice_section)
    val comprehensiveGamesButtonText = stringResource(id = R.string.home_comprehensive_games)
    val icon = painterResource(id = R.drawable.ic_icon_app)
    val titleTopTabBarText = stringResource(id = R.string.app_name)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText,
            navController = navController,
            showInfoButton = screenViewModel.shouldShowInfoButton(),
            onInfoButtonPressed = { screenViewModel.onClickInfoInfoIconButton() })
        }
    ) {
        if(screenViewModel.isError){
            ErrorScreen(navController = navController)
        }
        else if (screenViewModel.isLoading){
            FullScreenLoader()
            screenViewModel.loadCategories()
        }
        else{
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(20.dp))
                TitleText(text = titleText)
                Spacer(modifier = Modifier.height(70.dp))
                MainButton(text = learningSectionButtonText) {
                    navController.navigate(NavigationRoute.CategoriesLearning.route)
                }
                Spacer(modifier = Modifier.height(40.dp))
                MainButton(text = practiceSectionButtonText) {
                    navController.navigate(NavigationRoute.CategoriesPractice.route)
                }
                Spacer(modifier = Modifier.height(40.dp))
                MainButton(text = comprehensiveGamesButtonText, isEnabled = screenViewModel.isEnabledComprehensiveGamesButton) {
                    navController.navigate(NavigationRoute.StartComprehensiveGames.route)
                }
                FooterSloganAndIcon(icon = icon)

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
    

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    InstructorLsaTheme {
        HomeScreen(rememberNavController(), HomeScreenViewModel())
    }
}