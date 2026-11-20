package com.zirve.okey

import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
        "101 OKEY" to R.drawable.okey101,
        "BATAK" to R.drawable.batak,
        "TAVLA" to R.drawable.tavla,
        "SLOT OYUNLARI" to R.drawable.slot
    )

    val pagerState = rememberPagerState(
        pageCount = { games.size }
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        val compact = maxHeight < 400.dp

        // YENİ LÜKS SAHİL ARKA PLANI
        Image(
            painter = androidx.compose.ui.res.painterResource(
                id = R.drawable.zirve_arka_plan
            ),
            contentDescription = "Zirve Okey lüks sahil",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // ARKA PLAN KARARTMA
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.32f))
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // ÜST MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (compact) 52.dp else 62.dp)
                    .background(Color(0xCC350A26))
                    .padding(horizontal = 7.dp),
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

                    Text(
                        "VIP 0",
                        color = Color.White,
                        fontSize = 9.sp
                    )
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

                Text(
                    "⭐ Lv.1",
                    color = Gold,
                    fontSize = 11.sp
                )

                HeaderIcon("📬") {
                    selected = "Posta Kutusu"
                }

                HeaderIcon("⚙️") {
                    selected = "Ayarlar"
                }
            }

            // ORTA ALAN
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // SOL KISA YOLLAR
                Column(
                    modifier = Modifier.width(45.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val shortcuts = listOf(
                        "🏠" to "Ana Sayfa",
                        "❤️" to "Favoriler",
                        "👥" to "Arkadaşlar",
                        "💎" to "VIP"
                    )

                    shortcuts.forEach { item ->
                        HeaderIcon(item.first) {
                            selected = item.second
                        }
                    }
                }

                Spacer(Modifier.width(5.dp))

                // BÜYÜK KAYDIRILABİLİR OYUN LOGOLARI
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(250.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    pageSpacing = 8.dp,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) { page ->

                    val game = games[page]

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.94f),
                        contentAlignment = Alignment.Center
                    ) {

                        WhiteBackgroundLogo(
                            resourceId = game.second,
                            description = game.first,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clickable {
                                    selected = game.first
                                }
                        )
                    }
                }
            }

            // ALT MENÜ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (compact) 59.dp else 70.dp)
                    .background(Color(0xCC300A2D))
                    .padding(horizontal = 3.dp),
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

    // MENÜ PENCERESİ
    if (selected.isNotEmpty()) {

        AlertDialog(
            onDismissRequest = {
                selected = ""
            },
            containerColor = Dark,
            title = {
                Text(
                    selected,
                    color = Gold
                )
            },
            text = {
                Text(
                    "Bu bölümü sonraki geliştirmelerimizde açacağız.",
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selected = ""
                    }
                ) {
                    Text(
                        "TAMAM",
                        color = Gold
                    )
                }
            }
        )
    }
}

// BEYAZ ARKA PLANI ŞEFFAFLAŞTIRAN LOGO
@Composable
fun WhiteBackgroundLogo(
    resourceId: Int,
    description: String,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val logo = remember(resourceId) {

        val original = BitmapFactory.decodeResource(
            context.resources,
            resourceId
        )

        val bitmap = original.copy(
            Bitmap.Config.ARGB_8888,
            true
        )

        for (y in 0 until bitmap.height) {
            for (x in 0 until bitmap.width) {

                val pixel = bitmap.getPixel(x, y)

                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)

                // BEYAZA YAKIN PİKSELLERİ ŞEFFAF YAP
                if (
                    red > 242 &&
                    green > 242 &&
                    blue > 242
                ) {
                    bitmap.setPixel(
                        x,
                        y,
                        android.graphics.Color.TRANSPARENT
                    )
                }
            }
        }

        bitmap.asImageBitmap()
    }

    Image(
        bitmap = logo,
        contentDescription = description,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

// ÜST MENÜ BUTONU
@Composable
fun HeaderButton(
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFE68A),
                        Color(0xFFB56A12)
                    )
                ),
                RoundedCornerShape(9.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 6.dp,
                vertical = 8.dp
            )
    ) {

        Text(
            text,
            color = Color(0xFF42102A),
            fontSize = 9.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

// ÜST VE SOL İKONLAR
@Composable
fun HeaderIcon(
    icon: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(3.dp)
            .size(39.dp)
            .background(
                Color(0xCC671B43),
                RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            icon,
            fontSize = 22.sp
        )
    }
}

// ALT MENÜ BUTONLARI
@Composable
fun BottomButton(
    icon: String,
    label: String,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 3.dp,
                vertical = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            icon,
            fontSize = 22.sp
        )

        Text(
            label,
            color = Gold,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
