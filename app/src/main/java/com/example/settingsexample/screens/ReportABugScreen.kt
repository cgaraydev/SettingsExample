package com.example.settingsexample.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.settingsexample.components.MySpacer

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReportABugScreen(navController: NavController, darkMode: Boolean) {
    var email by remember { mutableStateOf("") }
    var report by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { MyTopAppBar(title = "Bug report", navController = navController, darkMode = darkMode) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
                .wrapContentSize(Alignment.Center)
                .clip(RectangleShape)
                .border(1.dp, Color.Gray)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Help us improve!",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                if (keyboardController != null) {
                    EmailTextField(
                        email = email,
                        onEmailChange = { email = it },
                        keyboardController = keyboardController
                    )
                }
                MySpacer(size = 16)
                if (keyboardController != null) {
                    BugReportTextField(
                        report = report,
                        onReportChange = { report = it },
                        keyboardController = keyboardController
                    )
                }
                MySpacer(size = 20)
                Button(
                    onClick = {
                        if (isValidEmail(email) && isValidReport(report)) {
                            Toast.makeText(context, "Report Submitted", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Invalid Email or Report", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    Text(text = "Submit")
                }
            }
        }

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController
) {
    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        label = { Text(text = "Enter your email") },
        maxLines = 2,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController.hide()
            }
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BugReportTextField(
    report: String,
    onReportChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController
) {
    OutlinedTextField(
        value = report,
        onValueChange = {
            onReportChange(it)
        },
        label = {
            Text(
                text = "Enter your bug report",
                textAlign = TextAlign.Start
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController.hide()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

fun isValidEmail(email: String): Boolean =
    email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isValidReport(report: String): Boolean = report.isNotBlank()
