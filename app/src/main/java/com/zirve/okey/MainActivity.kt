
package com.zirve.okey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

private val Gold = Color(0xFFFFD45C)
private val DarkRed = Color(0xFF450509)
private val Red = Color(0xFF9F1018)
private val Panel = Color(0xFF651018)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ZirveLobby()
            }
        }
    }
}

@Composable
fun ZirveLobby() {
    var selected by remember { mutableStateOf("") }

    val games = listOf(
        Triple("🎴", "101 OKEY", "Taşlarını diz, zirveye çık"),
        Triple("🃏", "BATAK", "Arkadaşlarınla yarış"),
        Triple("🎲", "TAVLA", "Rakiplerini yen"),
        Triple("🎰", "MİNİ OYUNLAR", "Eğlence seni bekliyor")
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF270306),
                        Color(0xFF8B1018),
                        Color(0xFF350407)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ÜST BİLGİ VE MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .background(Color(0xFF57070B))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("👑", fontSize = 27.sp)
                Column(modifier = Modifier.padding(start = 5.dp)) {
                    Text(
                        "ZİRVE",
                        color = Gold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp
                    )
                    Text("VIP 0", color = Color.White, fontSize = 10.sp)
                }

                Spacer(modifier = Modifier.weight(1f))

                GoldButton("🪙 1.000 +") {
                    selected = "Çip Mağazası"
                }

                Spacer(modifier = Modifier.width(6.dp))

                GoldButton("MAĞAZA") {
                    selected = "Mağaza"
                }

                Spacer(modifier = Modifier.width(6.dp))

                GoldButton("PAKET 🎁") {
                    selected = "Paketler"
                }

                Spacer(modifier = Modifier.weight(1f))

                Text("⭐ Lv.1", color = Gold, fontWeight = FontWeight.Bold)
                TopIcon("📬") { selected = "Posta Kutusu" }
                TopIcon("⚙️") { selected = "Ayarlar" }
            }

            // ORTA OYUN ALANI
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // SOL MENÜ
                Column(
                    modifier = Modifier
                        .width(54.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOf("🏠", "❤️", "🃏", "🎰", "💎").forEachIndexed { index, icon ->
                        MenuIcon(icon) {
                            selected = listOf(
                                "Ana Sayfa",
                                "Favoriler",
                                "Kart Oyunları",
                                "Mini Oyunlar",
                                "VIP"
                            )[index]
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // OYUN KARTLARI
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    games.forEach { game ->
                        GamePoster(
                            emoji = game.first,
                            title = game.second,
                            description = game.third,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            selected = game.second
                        }
                    }
                }
            }

            // ALT MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .background(Color(0xFF50070A))
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomItem("🪙", "ÜCRETSİZ\nÇİP") {
                    selected = "Ücretsiz Çip"
                }
                BottomItem("📋", "GÖREVLER") {
                    selected = "Görevler"
                }
                BottomItem("🎁", "GÜNLÜK\nGİRİŞ") {
                    selected = "Günlük Giriş"
                }
                BottomItem("👥", "ARKADAŞLAR") {
                    selected = "Arkadaşlar"
                }
                BottomItem("🎁", "HEDİYELER") {
                    selected = "Hediyeler"
                }
                BottomItem("🏆", "SIRALAMA") {
                    selected = "Sıralama"
                }
                BottomItem("💬", "SOHBET") {
                    selected = "Sohbet"
                }
            }
        }
    }

    if (selected.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { selected = "" },
            containerColor = DarkRed,
            title = {
                Text(selected, color = Gold)
            },
            text = {
                Text(
                    "Bu bölümü sıradaki geliştirmelerimizde çalışır hale getireceğiz.",
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(onClick = { selected = "" }) {
                    Text("TAMAM", color = Gold)
                }
            }
        )
    }
}

@Composable
fun GoldButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFE58A), Color(0xFFB87808))
                ),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 9.dp, vertical = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = Color(0xFF541000),
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun TopIcon(icon: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(39.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 23.sp)
    }
}

@Composable
fun MenuIcon(icon: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFB52B23), Color(0xFF500509))
                ),
                RoundedCornerShape(9.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 24.sp)
    }
}

@Composable
fun GamePoster(
    emoji: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Panel
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFB52A24),
                            Color(0xFF650A10),
                            Color(0xFF300407)
                        )
                    )
                )
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(emoji, fontSize = 34.sp)

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                title,
                color = Gold,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                description,
                color = Color.White,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "OYNA  ›",
                color = Color(0xFF5A1000),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Gold, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 7.dp)
            )
        }
    }
}

@Composable
fun BottomItem(
    emoji: String,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 3.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(emoji, fontSize = 22.sp)
        Text(
            label,
            color = Gold,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 10.sp
        )
    }
}
