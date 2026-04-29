package com.example.cafecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafecompose.data.Cafe
import com.example.cafecompose.ui.theme.DarkBrown
import com.example.cafecompose.ui.theme.DarkYellow
import com.example.cafecompose.ui.theme.SoftYellow

@Composable
fun CafeVerticalCard(cafe: Cafe, onDetail: () -> Unit, onMaps: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SoftYellow),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Image(
                painter = painterResource(cafe.image),
                contentDescription = null,
                modifier = Modifier.size(125.dp).padding(12.dp).clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp).fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(cafe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkBrown)
                Text(cafe.desc, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis, color = DarkBrown)
                Text("⭐ ${cafe.rating}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkYellow)

                Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Button(
                        onClick = onMaps,
                        modifier = Modifier.weight(1f).height(36.dp),
                        colors = ButtonDefaults.buttonColors(DarkBrown),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Maps", fontSize = 11.sp) }

                    Spacer(Modifier.width(6.dp))

                    Button(
                        onClick = onDetail,
                        modifier = Modifier.weight(1f).height(36.dp),
                        colors = ButtonDefaults.buttonColors(DarkBrown),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Detail", fontSize = 11.sp) }
                }
            }
        }
    }
}