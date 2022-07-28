package com.example.instructorlsa.ui.common.components.topTabBar

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.instructorlsa.R

@Composable
fun TopTabBarLsa(){
    val titleText = stringResource(id = R.string.app_name)
    TopAppBar(
        title = { TitleTopBarText(text = titleText) }
    )
}