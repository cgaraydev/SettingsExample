package com.example.settingsexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.settingsexample.data.DataStoreManager
import com.example.settingsexample.data.DataStoreManager.languageSelection
import com.example.settingsexample.R
import com.example.settingsexample.util.changeLocales
import com.example.settingsexample.util.getLocaleString
import kotlinx.coroutines.flow.first

@Composable
fun CardItem(
    icon: Int,
    text: String,
    actionIcon: ActionIcon?,
    onItemClick: () -> Unit = {}
) {
    var isOn by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White)
            .clickable {
                isOn = !isOn
                onItemClick()
            }
            .padding(start = 16.dp, end = 20.dp, top = 10.dp, bottom = 12.dp)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "")
        MySpacer(size = 10)
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        when (actionIcon) {

            ActionIcon.SWITCH -> Switch(
                checked = isOn,
                onCheckedChange = { isOn = it },
                modifier = Modifier.size(30.dp)
            )

            ActionIcon.ARROW -> Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )

            else -> {}
        }
    }
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}

@Composable
fun LanguageItem(
    language: String,
    isSelected: Boolean,
    onLanguageSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onLanguageSelected()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onLanguageSelected() })
        MySpacer(20)
        Text(text = language)
    }
}

@Composable
fun LanguageDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val context = LocalContext.current
    val languages = listOf(
        "English",
        "Spanish",
        "Italian",
        "French",
        "Portuguese"
    )
    var selectedLanguage by remember { mutableStateOf(languages[0]) }
    var isSelectionLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        selectedLanguage = context.languageSelection.first() ?: languages[0]
        isSelectionLoaded = true
    }

    LaunchedEffect(selectedLanguage) {
        DataStoreManager.saveLanguageSelection(context, selectedLanguage)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = stringResource(id = R.string.language)) },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    languages.forEach { language ->
                        LanguageItem(
                            language = language,
                            isSelected = language == selectedLanguage && isSelectionLoaded,
                            onLanguageSelected = {
                                selectedLanguage = language
                            })
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    changeLocales(context, getLocaleString(selectedLanguage))
                    onConfirm()
                }) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}

