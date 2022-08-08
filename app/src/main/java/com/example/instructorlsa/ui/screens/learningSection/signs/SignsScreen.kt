package com.example.instructorlsa.ui.screens.learningSection.signs

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
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.categories.components.CategoryCard
import com.example.instructorlsa.ui.screens.learningSection.signs.components.SignToLearnView
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoriesScreenViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.MockDataSigns
import com.example.instructorlsa.viewmodels.signs.SignViewModel
import com.example.instructorlsa.viewmodels.signs.SignsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignsScreen(navController: NavController, screenViewModel: SignsScreenViewModel) {
    val titleText = screenViewModel.category.name
    val titleTopTabBarText = stringResource(id = R.string.home_learning_section)
    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        if(screenViewModel.loading){
            FullScreenLoader()
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