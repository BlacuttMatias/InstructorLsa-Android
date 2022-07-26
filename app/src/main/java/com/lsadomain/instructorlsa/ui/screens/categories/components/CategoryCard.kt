package com.lsadomain.instructorlsa.ui.screens.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCard(category: CategoryViewModel, onClick: () -> Unit){
    var alpha = ContentAlpha.disabled
    if (category.enabled) {
        alpha = ContentAlpha.high
    }
    Card(
        elevation = 10.dp,
        onClick = onClick,
        enabled = category.enabled
    ) {
        Column(modifier = Modifier.fillMaxSize().alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = category.iconId),
                contentDescription = null,
                modifier = Modifier.size(100.dp).background(Color.White)
            )
            Text(text = category.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    InstructorLsaTheme {
        CategoryCard(category = CategoryViewModel(name = "Nombre categoria", iconId = R.drawable.ic_launcher_foreground), onClick = {})
    }
}