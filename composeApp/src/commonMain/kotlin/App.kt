import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.AppTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var isSystemInDarkTheme by remember { mutableStateOf(false) }

    AppTheme(isSystemInDarkTheme) {
        var greetingText by remember { mutableStateOf("Hello World!") }
        var showImage by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxSize()) {
            Row {
                Column(
                    Modifier.fillMaxHeight().width(48.dp).background(MaterialTheme.colorScheme.secondary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource("compose-multiplatform.xml"),
                        null
                    )
                    if (!isSystemInDarkTheme) {
                        IconButton(onClick = { isSystemInDarkTheme = true }) {
                            Icon(Icons.Rounded.LightMode, "LightMode", Modifier, Color.White)
                        }
                    } else {
                        IconButton(onClick = { isSystemInDarkTheme = false }) {
                            Icon(Icons.Rounded.DarkMode, "DarkMode")
                        }
                    }
                }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {
                        greetingText = "Compose: ${Greeting().greet()}"
                        showImage = !showImage
                    }) {
                        Text(greetingText)
                    }
                    AnimatedVisibility(showImage) {
                        Image(
                            painterResource("compose-multiplatform.xml"),
                            null
                        )
                    }
                }
            }
        }
    }
}