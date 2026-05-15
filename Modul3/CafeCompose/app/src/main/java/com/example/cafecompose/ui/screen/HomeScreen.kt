package com.example.cafecompose.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cafecompose.data.CafeData
import com.example.cafecompose.ui.components.CafeHorizontalCard
import com.example.cafecompose.ui.components.CafeVerticalCard
import com.example.cafecompose.ui.theme.DarkBrown

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val allList = CafeData.listCafe
    val featuredList = allList.filter { it.isFeatured }

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        item {
            Column(modifier = Modifier.padding(top = 24.dp)) {
                Text(
                    "Rekomendasi Nugas Hari Ini",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(featuredList) { cafe ->
                        CafeHorizontalCard(
                            cafe = cafe,
                            onDetail = {
                                navController.navigate("detail/${cafe.name}/${cafe.desc}/${cafe.image}/${cafe.rating}")
                            },
                            onMaps = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cafe.mapsUrl))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
                Text(
                    "Daftar Cafe",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 12.dp)
                )
            }
        }
        items(allList) { cafe ->
            CafeVerticalCard(
                cafe = cafe,
                onDetail = {
                    navController.navigate("detail/${cafe.name}/${cafe.desc}/${cafe.image}/${cafe.rating}")
                },
                onMaps = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cafe.mapsUrl))
                    context.startActivity(intent)
                }
            )
        }
    }
}