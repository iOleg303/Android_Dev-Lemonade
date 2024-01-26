package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LemonadeAppTheme {
                LemonadeAppPreview()
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colorScheme.background
    )
    {
        var imageResource by remember { mutableStateOf(R.drawable.lemon_tree) }
        var descResource by remember { mutableStateOf(R.string.lemon_tree_description) }
        var textResource by remember { mutableStateOf(R.string.tap_the_lemon_tree) }
        var numOfIteration by remember { mutableStateOf(0) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(
                        onClick = {
                            when (imageResource) {
                                R.drawable.lemon_tree -> {
                                    imageResource = R.drawable.lemon_squeeze
                                    descResource = R.string.lemon_description
                                    textResource = R.string.keep_tapping
                                    numOfIteration = (2..4).random()
                                }

                                R.drawable.lemon_squeeze -> {
                                    when (numOfIteration) {
                                        0 -> {
                                            imageResource = R.drawable.lemon_drink
                                            descResource =
                                                R.string.glass_of_lemonade_description
                                            textResource = R.string.tap_the_lemonade
                                        }

                                        else -> numOfIteration--
                                    }
                                }

                                R.drawable.lemon_drink -> {
                                    imageResource = R.drawable.lemon_restart
                                    descResource = R.string.empty_glass_description
                                    textResource = R.string.tap_the_empty_glass
                                }

                                else -> {
                                    imageResource = R.drawable.lemon_tree
                                    descResource = R.string.lemon_tree_description
                                    textResource = R.string.tap_the_lemon_tree
                                }
                            }
                        }),
                painter = painterResource(imageResource),
                contentDescription = stringResource(descResource)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(textResource), fontSize = 18.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}
