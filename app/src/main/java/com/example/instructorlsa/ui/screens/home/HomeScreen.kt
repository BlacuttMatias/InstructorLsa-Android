package com.example.instructorlsa.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
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
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.FooterSloganAndIcon
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.home.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavController, screenViewModel: HomeScreenViewModel) {
    val titleText = stringResource(id = R.string.home_main_menu)
    val learningSectionButtonText = stringResource(id = R.string.home_learning_section)
    val practiceSectionButtonText = stringResource(id = R.string.home_practice_section)
    val comprehensiveGamesButtonText = stringResource(id = R.string.home_comprehensive_games)
    val icon = painterResource(id = R.drawable.ic_icon_app)
    val titleTopTabBarText = stringResource(id = R.string.app_name)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
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
                MainButton(text = comprehensiveGamesButtonText) {

                }
                FooterSloganAndIcon(icon = icon)
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