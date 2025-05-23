package com.chatgptlite.wanted.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quicinc.chatapp.R
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onClickMenu: () -> Unit
) {
    ChatGPTLiteTheme() {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 0.dp,
        ) {
            CenterAlignedTopAppBar(
                title = {
                    val paddingSizeModifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .size(32.dp)
                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ursa_logo), //rememberAsyncImagePainter(urlToImageAppIcon),
                                modifier = paddingSizeModifier.then(Modifier.clip(RoundedCornerShape(6.dp))),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "TAI GPT",
                                textAlign = TextAlign.Center,
                                fontSize = 16.5.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onClickMenu()
                        },
//                        enabled = mlcModelSettingsViewModel?.chatState?.interruptable() == true
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            "backIcon",
                            modifier = Modifier.size(26.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = BackGroundColor,
                    titleContentColor = Color.White,
                ),
                actions = {
                    IconButton(
                        onClick = {  },
//                        enabled = mlcModelSettingsViewModel?.chatState?.interruptable() == true
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Replay,
                            contentDescription = "reset the chat",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onBackPressed: () -> Unit
) {
    ChatGPTLiteTheme() {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 0.dp,
        ) {
            CenterAlignedTopAppBar(
                title = {
                    val paddingSizeModifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .size(32.dp)
                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ursa_logo), //rememberAsyncImagePainter(urlToImageAppIcon),
                                modifier = paddingSizeModifier.then(Modifier.clip(RoundedCornerShape(6.dp))),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                fontSize = 16.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPressed()
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "backIcon",
                            modifier = Modifier.size(26.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = BackGroundColor,
                    titleContentColor = Color.White,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBar(
        onClickMenu = {  }
    )
}
