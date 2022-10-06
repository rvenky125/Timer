package com.example.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.timer.ui.theme.TimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color(0xff101010)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MediaQuery(
                            comparator = Dimensions.Width lessThan 600.dp,
                            falseContent = {
                                Text(text = "Large screen", color = Color.White)
                            }
                        ) {
                            Text(text = "Small screen", color = Color.White)
                        }

                        Box(
                            modifier = Modifier
                                .mediaQuery(
                                    Dimensions.Width lessThan 600.dp,
                                    trueModifier = Modifier
                                        .size(50.dp)
                                        .background(Color.Red),
                                    falseModifier = Modifier
                                        .size(150.dp)
                                        .background(Color.Green)
                                )
                        )
                        
                        /*Timer(
                            totalTime = 15L * 1000L,
                            handleColor = Color.Green,
                            inactiveBarColor = Color.DarkGray,
                            activeBarColor = Color(0xFF00C853),
                            modifier = Modifier.size(200.dp)
                        )
                        FillTimer(
                            totalTime = 15L * 1000L,
                            inactiveBarColor = Color.DarkGray,
                            activeBarColor = Color(0xFF00C853),
                            modifier = Modifier.size(200.dp)
                        )*/
                    }
                }
            }
        }
    }
}