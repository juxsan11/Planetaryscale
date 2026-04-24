package com.example.planetary_scale


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planetary_scale.ui.theme.Planetary_scaleTheme
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.roundToInt
import androidx.compose.runtime.*               //remember


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Planetary_scaleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    EightRowsScreen()
                }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EightRowsScreen() {

        val startDateTime = LocalDateTime.of(1969, 1, 1, 0, 0, 0)
        val secondsSinceYear = Duration.between(startDateTime, LocalDateTime.now()).seconds
        val SecsToYear = 86400;

        val duration = listOf(
            58.5,
            224,
            365,
            668,
            4380,
            10767.5,
            30660,
            60225
        )

        val items = listOf(
            "Mercurio",
            "Venus",
            "Tierra",
            "Marte",
            "Júpiter",
            "Saturno",
            "Urano",
            "Neptuno"
        )

    var expandedIndex by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(items) { index, text ->
            val isExpanded = expandedIndex == index

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
                    .padding(12.dp)
                    .clickable { expandedIndex = if (isExpanded) null else index },
                verticalAlignment = Alignment.CenterVertically
            ) {
                //memo: cambiar por imagen
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = text)
                    // segundos desde "fecha_og" son divdidos entre los segunos en cada año correspondiente se redondea y se stringea
                    Text(text = (secondsSinceYear / (duration[index].toDouble() * SecsToYear)).roundToInt().toString() +" años")

                    // Add el texto cuando se expande
                    if (isExpanded) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            //memo: agregar el texto para cada una
                            text = "row No. $index. cuiosidades planetarias",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
