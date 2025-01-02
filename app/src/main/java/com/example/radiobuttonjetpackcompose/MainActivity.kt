package com.example.radiobuttonjetpackcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun changeColor(
            context: Context,
            optionId: Int
        ): Color = Color(
            ContextCompat.getColor(
                context, when (optionId) {
                    R.drawable.blue -> R.color.blue
                    R.drawable.red -> R.color.red
                    R.drawable.green -> R.color.green
                    R.drawable.white -> R.color.white
                    R.drawable.brown -> R.color.brown
                    else -> R.color.purple_200
                }
            )
        )

        setContent {
            val context = LocalContext.current
            val cars = listOf(
                R.drawable.blue,
                R.drawable.red,
                R.drawable.green,
                R.drawable.white,
                R.drawable.brown
            )
            val (selectedOptionId, onOptionSelected) = rememberSaveable { mutableIntStateOf(cars[0]) }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = selectedOptionId),
                        contentDescription = "preview",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    Modifier
                        .selectableGroup()
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    cars.forEach { optionId ->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                                .background(
                                    color = changeColor(context, optionId),
                                    shape = CircleShape
                                )
                                .border(width = 2.dp, shape = CircleShape, color = Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            RadioButton(
                                selected = (optionId == selectedOptionId),
                                onClick = { onOptionSelected(optionId) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = changeColor(context, optionId),
                                    unselectedColor = changeColor(context, optionId)
                                )
                            )
                        }
                    }
                }

                val equipment = listOf(
                    "Classic",
                    "Comfort",
                    "Luxe",
                    "Style"
                )
                val (selectedOptionIdTwo, onOptionSelectedTwo) = rememberSaveable {
                    mutableStateOf(
                        equipment[0]
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                        .padding(top = 20.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    equipment.forEach { optionIdTwo ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (optionIdTwo == selectedOptionIdTwo),
                                onClick = { onOptionSelectedTwo(optionIdTwo) }
                            )
                            Text(text = optionIdTwo, fontSize = 24.sp)
                        }
                    }
                    val price = when (selectedOptionIdTwo) {
                        "Classic" -> 2104900
                        "Comfort" -> 2174900
                        "Luxe" -> 2289900
                        "Style" -> 2329900
                        else -> 0
                    }
                    Text(
                        text = "Стоимость: от ${price}₽",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}