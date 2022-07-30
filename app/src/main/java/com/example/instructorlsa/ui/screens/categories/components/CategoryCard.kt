package com.example.instructorlsa.ui.screens.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.screens.categories.CategoriesScreen
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

@Composable
fun CategoryCard(category: CategoryViewModel){
    Card(
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = category.iconId),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(text = category.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    InstructorLsaTheme {
        CategoryCard(category = CategoryViewModel(name = "Nombre categoria", iconId = R.drawable.ic_launcher_foreground))
    }
}