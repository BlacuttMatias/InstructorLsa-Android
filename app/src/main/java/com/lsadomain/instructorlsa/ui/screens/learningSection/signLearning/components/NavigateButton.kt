package com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lsadomain.instructorlsa.R

@Composable
fun NavigateButton(iconId: Int, onClick: () -> Unit) {
    val icon = painterResource(id = iconId)
    IconButton(modifier = Modifier.size(60.dp),
        onClick = onClick
    ){
        Icon(painter = icon, contentDescription = null, modifier = Modifier.size(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun NavigateButtonPreview() {
    NavigateButton(iconId = R.drawable.ic_navigate_next){}
}