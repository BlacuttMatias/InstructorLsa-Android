package com.lsadomain.instructorlsa.ui.screens.learningSection.signs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.ui.common.components.TitleText
import com.lsadomain.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.lsadomain.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.lsadomain.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.lsadomain.instructorlsa.ui.screens.learningSection.signs.components.SignToLearnView
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import com.lsadomain.instructorlsa.viewmodels.signs.MockDataSigns
import com.lsadomain.instructorlsa.viewmodels.signs.SignsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignsScreen(navController: NavController, screenViewModel: SignsScreenViewModel) {
    val titleText = screenViewModel.category.name
    val titleTopTabBarText = stringResource(id = R.string.home_learning_section)
    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        if(screenViewModel.isError) {
            ErrorScreen(navController = navController)
        }
        else if (screenViewModel.loading) {
            FullScreenLoader()
            screenViewModel.loadInitData()
        }
        else{
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(20.dp))
                TitleText(text = titleText)
                Spacer(modifier = Modifier.height(20.dp))
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(screenViewModel.signs){ sign ->
                        SignToLearnView(sign = sign) {
                            InstructorLsaConfig.indexSignToLearn = screenViewModel.signs.indexOfFirst {
                                it == sign
                            }
                            InstructorLsaConfig.learningSigns = screenViewModel.signs
                            navController.navigate(NavigationRoute.SignLearning.route)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignsScreenPreview() {
    val mockSignsViewModel = MockDataSigns.signs
    val mockCategory = CategoryViewModel(name = "Colores", iconId = 0)
    InstructorLsaTheme {
        SignsScreen(rememberNavController(), SignsScreenViewModel(category = mockCategory))
    }
}