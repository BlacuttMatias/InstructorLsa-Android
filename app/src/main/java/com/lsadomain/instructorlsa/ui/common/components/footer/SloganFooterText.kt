package com.lsadomain.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SloganFooterText(bottomSpace: Dp = 40.dp) {
    FooterColumn {
        SloganText()
        Spacer(modifier = Modifier.height(bottomSpace))
    }
}