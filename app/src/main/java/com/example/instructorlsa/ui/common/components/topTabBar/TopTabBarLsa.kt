package com.example.instructorlsa.ui.common.components.topTabBar

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopTabBarLsa(titleText: String){
    TopAppBar(
        title = { TitleTopBarText(text = titleText) }
    )
}