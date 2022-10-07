package com.example.instructorlsa.ui.screens.practiceSection.Games

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.FooterSloganAndIcon
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.viewmodels.games.signTheWord.InfoSignTheWordScreenViewModel

@Composable
fun InfoSignTheWordScreen(screenViewModel: InfoSignTheWordScreenViewModel, navController: NavController) {

    val titleTopTabBarText = screenViewModel.getTitleTopBar()
    val titleText = screenViewModel.getTitle()
    val messages = screenViewModel.getMessages()
    val icon = painterResource(id = R.drawable.ic_icon_app)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            val bullet = "\u2022"
            val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
            Row() {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = buildAnnotatedString {
                        messages.forEach {
                            withStyle(style = paragraphStyle) {
                                append(bullet)
                                append("\t\t")
                                append(it)
                                append("\n")
                            }
                        }
                    },
                    fontSize = 18.sp
                )
            }

//            LazyColumn {
//                items(messages) {
//                    Row(Modifier.padding(8.dp),verticalAlignment = Alignment.CenterVertically) {
//                        Canvas(modifier = Modifier.padding(start = 8.dp,end = 8.dp).size(6.dp)){
//                            drawCircle(Color.White)
//                        }
//                        Text(text = it,fontSize = 12.sp)
//                    }
//                }
//            }
        }
    }
}