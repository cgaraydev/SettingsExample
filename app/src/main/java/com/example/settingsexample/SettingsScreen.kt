package com.example.settingsexample

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.settingsexample.components.ActionIcon
import com.example.settingsexample.components.CardItem
import com.example.settingsexample.components.LanguageDialog
import com.example.settingsexample.components.MySpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.settings)) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MySpacer(size = 30)
            SettingsHeader()
            MySpacer(size = 20)
            SettingsBody()
        }
    }
}

@Composable
fun SettingsBody() {
    var show by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(20.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        CardItem(
            icon = R.drawable.ic_dark,
            text = stringResource(R.string.dark_mode),
            actionIcon = ActionIcon.SWITCH
        )
        CardItem(
            icon = R.drawable.ic_notification,
            text = stringResource(R.string.push_notifications),
            actionIcon = ActionIcon.SWITCH
        )
        Divider(thickness = 1.dp, color = Color.Black)
        CardItem(
            icon = R.drawable.ic_account,
            text = stringResource(R.string.account),
            actionIcon = ActionIcon.ARROW
        )
        CardItem(
            icon = R.drawable.ic_language,
            text = stringResource(R.string.language),
            actionIcon = null,
            onItemClick = { show = true }
        )
        if (show) {
            LanguageDialog(
                showDialog = true,
                onDismiss = { show = false },
                onConfirm = { show = false }
            )
        }
        CardItem(
            icon = R.drawable.ic_accessibility,
            text = stringResource(R.string.accessibility),
            actionIcon = ActionIcon.ARROW
        )
        CardItem(
            icon = R.drawable.ic_bug_report,
            text = stringResource(R.string.report_a_bug),
            actionIcon = ActionIcon.ARROW
        )
        CardItem(
            icon = R.drawable.ic_help,
            text = stringResource(R.string.help_and_faq),
            actionIcon = ActionIcon.ARROW
        )
        CardItem(
            icon = R.drawable.ic_info,
            text = stringResource(R.string.about_us),
            actionIcon = null
        )
    }
}

@Composable
fun SettingsHeader() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }
    Box {
        val modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape)
            .clickable { launcher.launch("image/*") }
        if (imageUri == null) {
            AsyncImage(
                model = R.drawable.ic_launcher_background,
                contentDescription = "",
                modifier = modifier,
            )
        } else {
            AsyncImage(
                model = imageUri,
                contentDescription = "",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .align(Alignment.BottomEnd)
                .padding(4.dp)
        )
    }
    MySpacer(size = 10)
    Text(text = "user462157", style = MaterialTheme.typography.bodyLarge)
}





