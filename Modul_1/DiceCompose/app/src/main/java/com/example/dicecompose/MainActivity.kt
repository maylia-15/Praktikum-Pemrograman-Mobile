package com.example.dicecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF121212)
            ) {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    var dice1 by remember { mutableIntStateOf(0) }
    var dice2 by remember { mutableIntStateOf(0) }

    val resultMessage = when {
        dice1 == 0 -> ""
        dice1 == dice2 -> stringResource(R.string.status_double)
        else -> stringResource(R.string.status_not_lucky)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DiceImage(dice1)
                Spacer(modifier = Modifier.width(16.dp))
                DiceImage(dice2)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    dice1 = (1..6).random()
                    dice2 = (1..6).random()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD1B2E0),
                    contentColor = Color(0xFF4A148C)
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                modifier = Modifier.width(90.dp) .height(45.dp)
            ) {
                Text(
                    text = stringResource(R.string.roll_button),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        if (resultMessage.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0))
                    .padding(16.dp)
            ) {
                Text(
                    text = resultMessage,
                    color = Color(0xFF454545),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun DiceImage(number: Int) {
    val imageResource = when (number) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}