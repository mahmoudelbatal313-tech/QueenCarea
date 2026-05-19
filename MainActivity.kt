package com.queencare.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { QueenCareApp() }
    }
}

private val Cream = Color(0xFFF7EFE4)
private val SoftGold = Color(0xFFC8A45D)
private val DustyMauve = Color(0xFFB89AB5)
private val LightMauve = Color(0xFFE8D9E8)
private val DarkBrown = Color(0xFF3A2A24)

@Composable
fun QueenCareApp() {
    val context = LocalContext.current
    val scroll = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cream
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .background(Brush.verticalGradient(listOf(Cream, LightMauve)))
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(Modifier.height(18.dp))

            PromoCard()
            Spacer(Modifier.height(16.dp))

            SectionTitle("خدمات البشرة")
            ServiceGrid(listOf(
                "تنظيف بشرة بسيط وعميق",
                "ديرمابن نضارة وعلاجية",
                "تقشير بارد وكيميائي",
                "جلسات بلازما",
                "سكين بوستر ياقوت وعنبر",
                "اكسجينو وتوريد"
            ))

            Spacer(Modifier.height(16.dp))
            SectionTitle("خدمات الشعر")
            ServiceGrid(listOf(
                "تريتمنت بارد وحراري",
                "بلازما للشعر",
                "ميزوثيرابي إنبات وتقوية",
                "بروتين وفيلر وبوتوكس وكافيار",
                "ديتوكس لعلاج قشرة الشعر"
            ))

            Spacer(Modifier.height(18.dp))
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/201013005666?text=أهلاً Queen Care، عايزة أحجز جلسة"))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = SoftGold),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("احجزي الآن على واتساب", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01013005666"))
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(54.dp)
            ) {
                Text("اتصال مباشر: 01013005666", color = DarkBrown, fontSize = 16.sp)
            }

            Spacer(Modifier.height(18.dp))
            Text(
                text = "📍 الحوامدية - برج السلام الطبي - الدور الثالث أعلى معامل المدينة",
                color = DarkBrown,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Queen Care 👑", fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, color = DarkBrown)
        Text("مركز العناية بالبشرة والشعر", fontSize = 18.sp, color = DarkBrown)
        Text("Luxury • Beauty • Care", fontSize = 14.sp, color = SoftGold, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun PromoCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("عروض Queen Care", color = SoftGold, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(8.dp))
            Text("3 جلسات بسعر مميز", color = DarkBrown, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("سكين بوستر + تنظيف بشرة + بلازما", color = DarkBrown, fontSize = 16.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("خصومات وعروض لفترة محدودة", color = DustyMauve, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(title, color = DarkBrown, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Right)
    Spacer(Modifier.height(8.dp))
}

@Composable
fun ServiceGrid(items: List<String>) {
    Column(Modifier.fillMaxWidth()) {
        items.chunked(2).forEach { rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                rowItems.forEach { item -> ServiceCard(item, Modifier.weight(1f)) }
                if (rowItems.size == 1) Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun ServiceCard(text: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(86.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.78f)),
        shape = RoundedCornerShape(22.dp)
    ) {
        Box(Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center) {
            Text(text, color = DarkBrown, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
        }
    }
}
