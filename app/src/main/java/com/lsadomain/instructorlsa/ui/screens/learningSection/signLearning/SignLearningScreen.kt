package com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lsadomain.instructorlsa.constants.Constants
import com.lsadomain.instructorlsa.ui.common.components.SloganFooterText
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.lsadomain.instructorlsa.ui.common.components.loadingScreen.OverlapFullScreenLoader
import com.lsadomain.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components.BackNavigateButton
import com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components.NextNavigateButton
import com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components.VideoPlayer
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.categories.CategoriesScreenViewModel
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryLearningNavigation
import com.lsadomain.instructorlsa.viewmodels.signs.SignLearningScreenViewModel
import com.lsadomain.instructorlsa.viewmodels.signs.SignViewModel

@Composable
fun SignLearningScreen(screenViewModel: SignLearningScreenViewModel, navController: NavController) {
    val titleText = screenViewModel.getCurrentSign().name
    val titleTopTabBarText = screenViewModel.category.name

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        if(screenViewModel.isError){
            ErrorScreen(navController = navController)
        }
        else{
            OverlapFullScreenLoader(showLoader = screenViewModel.isLoading()) {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                    Spacer(modifier = Modifier.height(50.dp))
                    TitleText(text = titleText)
                    Spacer(modifier = Modifier.height(60.dp))
                    VideoPlayer(urlVideo = screenViewModel.getCurrentSign().urlVideo, delegate = screenViewModel)
                    Spacer(modifier = Modifier.height(60.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        if(screenViewModel.currentIndex > 0){
                            BackNavigateButton {
                                screenViewModel.didBackButtonClicked()
                            }
                        }
                        else{
                            Spacer(modifier = Modifier.width(60.dp))
                        }
                        Spacer(modifier = Modifier.width(50.dp))
                        if(screenViewModel.currentIndex < screenViewModel.signs.size-1){
                            NextNavigateButton {
                                screenViewModel.didNextButtonClicked()
                            }
                        }
                        else{
                            Spacer(modifier = Modifier.width(60.dp))
                        }
                    }
                    SloganFooterText()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignLearningScreenPreview() {
    InstructorLsaTheme {
        val category = CategoriesScreenViewModel(
            navigationStrategy = CategoryLearningNavigation(),
            categoriesViewModel = Constants.allBaseCategories
        ).getAllCategories().first()
        val sign = SignViewModel(
            id = 1,
            name = "Una seña",
            urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4",
            isCompleted = false
        )
        SignLearningScreen(SignLearningScreenViewModel(category, listOf(sign), 0), rememberNavController())
    }
}