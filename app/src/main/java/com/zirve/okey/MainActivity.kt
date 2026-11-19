
package com.zirve.okey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkBlue = Color(0xFF101A35)
private val Gold = Color(0xFFFFD166)
private val CardBlue = Color(0xFF1D2B50)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ZirveOkeyApp()
            }
        }
    }
}

@Composable
fun ZirveOkeyApp() {
    var selectedGame by remember { mutableStateOf("") }

    val games = listOf(
        "🎴" to "101 Okey",
        "🃏" to "Batak",
        "🎲" to "Tavla",
        "🎰" to "Mini Oyunlar"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(DarkBlue, Color(0xFF263D68))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "ZİRVE OKEY",
                color = Gold,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Eğlencenin zirvesine hoş geldin!",
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = CardBlue
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "👤 Oyuncu Profili",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Misafir Oyuncu",
                        color = Gold
                    )
                    Text(
                        text = "🪙 1.000 Ücretsiz Çip",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "OYUNLAR",
                color = Gold,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            games.forEach { game ->
                GameCard(
                    emoji = game.first,
                    name = game.second,
                    onClick = { selectedGame = game.second }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "SOSYAL ALAN",
                color = Gold,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            val socialItems = listOf(
                "👥 Arkadaşlar",
                "🎁 Hediyeler",
                "📬 Posta Kutusu",
                "💬 Sohbet",
                "🏆 Rozetler",
                "👑 VIP Üyelik",
                "🛍️ Çip Mağazası",
                "⚙️ Ayarlar"
            )

            socialItems.forEach { item ->
                GameCard(
                    emoji = item.substringBefore(" "),
                    name = item.substringAfter(" "),
                    onClick = { selectedGame = item.substringAfter(" ") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "ZİRVE OKEY • 1.0",
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
    }

    if (selectedGame.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { selectedGame = "" },
            title = {
                Text(selectedGame)
            },
            text = {
                Text("Bu bölümün özelliklerini birlikte geliştireceğiz.")
            },
            confirmButton = {
                TextButton(onClick = { selectedGame = "" }) {
                    Text("Tamam")
                }
            }
        )
    }
}

@Composable
fun GameCard(
    emoji: String,
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = CardBlue
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = name,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "›",
                color = Gold,
                fontSize = 30.sp
            )
        }
    }
}
