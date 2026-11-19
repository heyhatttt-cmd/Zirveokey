package com.zirve.okey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Gold = Color(0xFFFFD66B)
private val Dark = Color(0xFF170927)

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
        Triple("🎴", "101 OKEY", "Taşlarını diz"),
        Triple("🃏", "BATAK", "Kartlarını dağıt"),
        Triple("🎲", "TAVLA", "Zarını at"),
        Triple("🎰", "SLOT OYUNLARI", "Eğlenceye katıl")
    )

    val colors = listOf(
        listOf(Color(0xFFFF7B35), Color(0xFF8C174B)),
        listOf(Color(0xFF3489D8), Color(0xFF30206E)),
        listOf(Color(0xFF18A88A), Color(0xFF164C70)),
        listOf(Color(0xFFB52DCA), Color(0xFF541078))
    )

    val pagerState = rememberPagerState(pageCount = { games.size })

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val compact = maxHeight < 400.dp

        // SAHİL ARKA PLANI
Image(
    painter = painterResource(
        id = R.drawable.file_00000000376c82438d5b4ea285e34559
    ),
    contentDescription = "Zirve Okey sahil",
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop
)

        // YAZILARIN OKUNMASI İÇİN HAFİF KARARTMA
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.38f))
        )

        Column(modifier = Modifier.fillMaxSize()) {

            // ÜST MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (compact) 52.dp else 62.dp)
                    .background(Color(0xCC350A26))
                    .padding(horizontal = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("👑", fontSize = 27.sp)

                Column {
                    Text(
                        "ZİRVE OKEY",
                        color = Gold,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text("VIP 0", color = Color.White, fontSize = 9.sp)
                }

                Spacer(Modifier.weight(1f))

                HeaderButton("🪙 1.000") {
                    selected = "Çip Mağazası"
                }

                HeaderButton("MAĞAZA") {
                    selected = "Mağaza"
                }

                HeaderButton("🎁 PAKET") {
                    selected = "Paketler"
                }

                Text("⭐ Lv.1", color = Gold, fontSize = 12.sp)

                HeaderIcon("📬") { selected = "Posta Kutusu" }
                HeaderIcon("⚙️") { selected = "Ayarlar" }
            }

            // ORTA ALAN
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // SOL KISA YOL
                Column(
                    modifier = Modifier.width(48.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOf("🏠", "❤️", "👥", "💎").forEachIndexed { i, icon ->
                        HeaderIcon(icon) {
                            selected = listOf(
                                "Ana Sayfa",
                                "Favoriler",
                                "Arkadaşlar",
                                "VIP"
                            )[i]
                        }
                    }
                }

                Spacer(Modifier.width(8.dp))

                // KAYDIRILABİLİR OYUN KARTLARI
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(230.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    pageSpacing = 12.dp,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) { page ->
                    val game = games[page]

                    GameTile(
                        emoji = game.first,
                        title = game.second,
                        subtitle = game.third,
                        tileColors = colors[page],
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.86f)
                    ) {
                        selected = game.second
                    }
                }
            }

            // ALT MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (compact) 59.dp else 70.dp)
                    .background(Color(0xCC300A2D))
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomButton("🪙", "ÜCRETSİZ ÇİP") {
                    selected = "Ücretsiz Çip"
                }
                BottomButton("📋", "GÖREVLER") {
                    selected = "Görevler"
                }
                BottomButton("🎁", "GÜNLÜK GİRİŞ") {
                    selected = "Günlük Giriş"
                }
                BottomButton("👥", "ARKADAŞLAR") {
                    selected = "Arkadaşlar"
                }
                BottomButton("💝", "HEDİYELER") {
                    selected = "Hediyeler"
                }
                BottomButton("🏆", "SIRALAMA") {
                    selected = "Sıralama"
                }
                BottomButton("💬", "SOHBET") {
                    selected = "Sohbet"
                }
            }
        }
    }

    if (selected.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { selected = "" },
            containerColor = Dark,
            title = {
                Text(selected, color = Gold)
            },
            text = {
                Text(
                    "Bu bölümü sonraki geliştirmelerimizde açacağız.",
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
fun HeaderButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFE68A), Color(0xFFB56A12))
                ),
                RoundedCornerShape(9.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Text(
            text,
            color = Color(0xFF42102A),
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun HeaderIcon(
    icon: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(3.dp)
            .size(39.dp)
            .background(Color(0xCC671B43), RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 22.sp)
    }
}

@Composable
fun GameTile(
    emoji: String,
    title: String,
    subtitle: String,
    tileColors: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(17.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(tileColors))
                .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        Color.White.copy(alpha = 0.12f),
                        RoundedCornerShape(13.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 48.sp)
            }

            Spacer(Modifier.height(7.dp))

            Text(
                title,
                color = Gold,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Text(
                subtitle,
                color = Color.White,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "OYNA  ›",
                color = Color(0xFF39102B),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 11.sp,
                modifier = Modifier
                    .background(Gold, RoundedCornerShape(9.dp))
                    .padding(horizontal = 13.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
fun BottomButton(
    icon: String,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 3.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(icon, fontSize = 22.sp)
        Text(
            label,
            color = Gold,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
