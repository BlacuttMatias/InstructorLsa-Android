@file:OptIn(ExperimentalFoundationApi::class)

package com.example.instructorlsa.ui.screens.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.constants.Constants
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.categories.components.CategoryCard
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoriesScreenViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryLearningNavigation
import com.example.instructorlsa.viewmodels.categories.CategoryPracticeNavigation
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

@Composable
fun CategoriesScreen(navController: NavController, screenViewModel: CategoriesScreenViewModel) {
    val titleText = stringResource(id = R.string.categories)
    val titleTopTabBarText = screenViewModel.titleText
    val categories = screenViewModel.getAllCategories()
    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(categories){ category ->
                    CategoryCard(category = category){
                        screenViewModel.navigateToNextScreen(navController, category)
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    InstructorLsaTheme {
        CategoriesScreen(
            rememberNavController(),
            CategoriesScreenViewModel(
                navigationStrategy = CategoryLearningNavigation(),
                categoriesViewModel = Constants.allBaseCategories
            )
        )
    }
}