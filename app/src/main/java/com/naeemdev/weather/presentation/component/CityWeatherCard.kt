package com.naeemdev.weather.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naeemdev.weather.R
import com.naeemdev.weather.ui.theme.BlueGradientStart
import com.naeemdev.weather.ui.theme.PinkGradientEnd

@Composable
fun CityWeatherCard(
    modifier: Modifier = Modifier,
    degree: String,
    city: String,
    country: String,
    description: String,
    weatherImage: Int,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(BlueGradientStart,PinkGradientEnd)
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherInfo(
                modifier = Modifier.weight(1f),
                degree = degree,
                city = city,
                country = country,
                description = description
            )
            WeatherImage(
                weatherImage = weatherImage,
                modifier = Modifier
                    .size(160.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun WeatherImage(modifier: Modifier = Modifier, weatherImage: Int) {
    Image(
        painter = painterResource(id = weatherImage),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
private fun WeatherInfo(
    modifier: Modifier = Modifier,
    degree: String,
    city: String,
    country: String,
    description: String,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(vertical = 24.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "$degree°",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.25f),
                        offset = androidx.compose.ui.geometry.Offset(x = 2f, y = 4f),
                        blurRadius = 8f
                    )
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White.copy(alpha = 0.8f)
                )
            )
        }

        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = city,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Text(
                text = country,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(alpha = 0.85f)
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ModernCityWeatherCardPreview() {
    MaterialTheme {
        CityWeatherCard(
            modifier = Modifier.padding(16.dp),
            degree = "23",
            city = "Islamabad",
            country = "Pakistan",
            description = "Sunny",
            weatherImage = R.drawable.clear_sky_day
        )
    }
}