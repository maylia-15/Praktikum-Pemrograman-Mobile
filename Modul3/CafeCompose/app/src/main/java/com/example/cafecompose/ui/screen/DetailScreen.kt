package com.example.cafecompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafecompose.ui.theme.DarkBrown
import com.example.cafecompose.ui.theme.DarkYellow
import com.example.cafecompose.ui.theme.SoftYellow

@Composable
fun DetailScreen(name: String, desc: String, image: Int, rating: String, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(SoftYellow).verticalScroll(rememberScrollState())) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            Image(painterResource(image), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(16.dp).background(Color.White.copy(0.5f), CircleShape)
            ) { Icon(Icons.Default.ArrowBack, null, tint = DarkBrown) }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(name, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
            Text("⭐ $rating", color = DarkYellow, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
            HorizontalDivider(Modifier.padding(vertical = 16.dp), color = Color.LightGray.copy(alpha = 0.5f))
            Text("About this Cafe", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
            Text(desc, fontSize = 14.sp, lineHeight = 22.sp, color = DarkBrown, modifier = Modifier.padding(top = 8.dp))
        }
    }
}