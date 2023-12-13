package pages.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatApp() {
    Box(Modifier.fillMaxSize()) {
        SendMessage { text ->
            println(text)
        }
    }
}

@Composable
fun SendMessage(sendMessage: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        value = inputText,
        placeholder = {
            Text("Type message...")
        },
        onValueChange = {
            inputText = it
        },
        trailingIcon = {
            if (inputText.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .clickable {
                            sendMessage(inputText)
                            inputText = ""
                        }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("发送")
                }
            }
        }
    )
}