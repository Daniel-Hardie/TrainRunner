/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.trainrunner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.trainrunner.R
import com.example.trainrunner.presentation.theme.TrainRunnerTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp("Hermione")
        }
    }
}

@Preview(device = "id:wearos_small_round")
@Composable
fun WearApp(greetingName: String="test") {
    var number = remember { mutableStateOf(0)}

    // https://developer.android.com/develop/ui/views/notifications/build-notification#kotlin
    var builder = NotificationCompat.Builder(this, "12345")
//        .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("Title")
        .setContentText("Content")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    TrainRunnerTheme {
        Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                contentAlignment = Alignment.Center
        ) {
            TimeText()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Greeting(greetingName = greetingName, count = number.value)

                Button(
                    onClick = {
                        number.value++
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    modifier = Modifier
                        .width(width=160.dp)
                ) {
                    Text(text = "Increment")
                }

                Button(
                    onClick = {
                        number.value = 0
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    modifier = Modifier
                        .width(width=160.dp)
                ) {
                    Text(text = "Reset")
                }
            }
        }
    }
}

// good example of using resources
@Composable
fun Greeting(greetingName: String, count: Int) {
    Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.hello_world, greetingName, count)
    )
}

// good example of using resources
@Composable
fun IncrementNumber() {


}