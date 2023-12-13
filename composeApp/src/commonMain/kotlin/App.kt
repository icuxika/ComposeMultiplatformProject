import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import pages.PageType
import pages.chat.ChatApp
import pages.index.Index
import ui.theme.AppTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var isSystemInDarkTheme by remember { mutableStateOf(false) }
    val pageType = remember { mutableStateOf(PageType.INDEX) }

    AppTheme(isSystemInDarkTheme) {
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
                    SideBar(pageType)
                }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Content(pageType)
                }
            }
        }
    }
}

@Composable
fun SideBar(pageType: MutableState<PageType>) {
    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(arrayListOf(PageType.INDEX, PageType.CHAT)) { _, item ->
            IconButton(onClick = { pageType.value = item }) {
                when (item) {
                    PageType.INDEX -> Icon(Icons.Rounded.Home, item.name)
                    PageType.CHAT -> Icon(Icons.Rounded.Chat, item.name)
                }
            }
        }
    }
}

@Composable
fun Content(pageType: MutableState<PageType>) {
    Crossfade(pageType) { type ->
        when (type.value) {
            PageType.INDEX -> Index()
            PageType.CHAT -> ChatApp()
        }
    }
}