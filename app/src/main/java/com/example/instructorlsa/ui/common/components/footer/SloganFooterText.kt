package com.example.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R

@Composable
fun SloganFooterText() {
    FooterColumn {
        SloganText()
        Spacer(modifier = Modifier.height(80.dp))
    }
}