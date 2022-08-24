package com.example.instructorlsa.ui.common.components.textFields

import android.view.ViewTreeObserver
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.TextFieldDelegate
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameTextField(initText: String = "", delegate: TextFieldDelegate){
    var text by remember { mutableStateOf(initText) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            scope.launch { bringIntoViewRequester.bringIntoView() }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { view.viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            delegate.setTextTyped(text)
        },
        singleLine = true,
        label = { Text(text = "Ingresá la palabra signada") },
        placeholder = { Text(text = "Respuesta") },
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .bringIntoViewRequester(bringIntoViewRequester)
    )
}