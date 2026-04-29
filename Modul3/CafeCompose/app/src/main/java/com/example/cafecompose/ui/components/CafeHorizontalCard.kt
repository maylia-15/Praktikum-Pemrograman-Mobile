package com.example.cafecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafecompose.data.Cafe
import com.example.cafecompose.ui.theme.DarkBrown
import com.example.cafecompose.ui.theme.SoftYellow

@Composable
fun CafeHorizontalCard(cafe: Cafe, onDetail: () -> Unit, onMaps: () -> Unit) {
    Card(
        modifier = Modifier.width(340.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SoftYellow),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.size(120.dp),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Image(
                    painter = painterResource(cafe.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.padding(start = 16.dp).weight(1f)
            ) {
                Text(cafe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkBrown)
                Text(
                    text = cafe.desc,
                    maxLines = 2,
                    fontSize = 13.sp,
                    color = Color(0xFF6D4C41),
                    overflow = TextOverflow.Ellipsis
                )

                Row(modifier = Modifier.padding(top = 12.dp)) {
                    Button(
                        onClick = onMaps,
                        modifier = Modifier.weight(1f).height(38.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Maps", color = Color.White, fontSize = 11.sp) }

                    Spacer(Modifier.width(8.dp))

                    Button(
                        onClick = onDetail,
                        modifier = Modifier.weight(1f).height(38.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Detail", color = Color.White, fontSize = 11.sp) }
                }
            }
        }
    }
}