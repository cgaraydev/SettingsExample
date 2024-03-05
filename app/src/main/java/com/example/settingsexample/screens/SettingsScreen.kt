package com.example.settingsexample.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.settingsexample.R
import com.example.settingsexample.components.ActionIcon
import com.example.settingsexample.components.CardItem
import com.example.settingsexample.components.LanguageDialog
import com.example.settingsexample.components.MySpacer
import com.example.settingsexample.navigation.ScreensList
import com.example.settingsexample.ui.theme.SettingsExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    var darkMode by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.settings)) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MySpacer(size = 30)
            SettingsHeader()
            MySpacer(size = 20)
            SettingsExampleTheme(darkTheme = darkMode) {
                SettingsBody(navController, darkMode) {
                    darkMode = it
                }
            }
        }
    }
}

@Composable
fun SettingsBody(
    navController: NavController,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    var show by remember { mutableStateOf(false) }
    SettingsExampleTheme(darkTheme = darkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                CardItem(
                    icon = R.drawable.ic_dark,
                    text = stringResource(R.string.dark_mode),
                    actionIcon = ActionIcon.SWITCH,
                    onItemClick = { onDarkModeChange(!darkMode) }
                )
                CardItem(
                    icon = R.drawable.ic_notification,
                    text = stringResource(R.string.push_notifications),
                    actionIcon = ActionIcon.SWITCH
                )
                Divider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 50.dp)
                )
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
                    actionIcon = ActionIcon.ARROW,
                    onItemClick = { navController.navigate(ScreensList.ReportABugScreen.name) }
                )
                CardItem(
                    icon = R.drawable.ic_help,
                    text = stringResource(R.string.help_and_faq),
                    actionIcon = ActionIcon.ARROW
                )
                CardItem(
                    icon = R.drawable.ic_info,
                    text = stringResource(R.string.about_us),
                    actionIcon = null,
                    onItemClick = { navController.navigate(ScreensList.AboutUsScreen.name) }
                )
            }
        }

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
    SettingsExampleTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
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
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_edit),
//                    contentDescription = "",
//                    tint = Color.White,
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .background(Color.Black)
//                        .align(Alignment.BottomEnd)
//                        .padding(4.dp)
//                )
                }
                MySpacer(size = 10)
                Text(
                    text = "dasdasda",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}





