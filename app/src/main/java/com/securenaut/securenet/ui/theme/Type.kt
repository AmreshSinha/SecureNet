package com.securenaut.securenet.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.securenaut.securenet.R

val fonts = FontFamily(
    Font(R.font.inter_regular)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 57.sp,
        lineHeight = 72.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.5).sp,
        color = Black
    ),
    displayMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 45.sp,
        lineHeight = 56.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        color = Black
    ),
    displaySmall = TextStyle(
        fontFamily = fonts,
        fontSize = 36.sp,
        lineHeight = 48.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        color = Black
    ),
    headlineLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp,
        color = Black
    ),
    headlineMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp,
        color = Black
    ),
    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp,
        color = Black
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.15.sp,
        color = Black
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp,
        color = Black
    ),
    titleSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp,
        color = Black
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.5.sp,
        color = Black
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.25.sp,
        color = Black
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.4.sp,
        color = Black
    ),
    labelLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp,
        color = Black
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.5.sp,
        color = Black
    ),
    labelSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 11.sp,
        lineHeight = 17.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.5.sp,
        color = Black
    )
)
