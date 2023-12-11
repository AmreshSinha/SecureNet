package com.securenaut.securenet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.securenaut.securenet.R

val fonts = FontFamily(
    Font(R.font.inter_black),
    Font(R.font.inter_bold),
    Font(R.font.inter_extrabold),
    Font(R.font.inter_extralight),
    Font(R.font.inter_light),
    Font(R.font.inter_medium),
    Font(R.font.inter_regular),
    Font(R.font.inter_semibold),
    Font(R.font.inter_thin)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 57.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 45.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fonts,
        fontSize = 36.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 32.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 28.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 24.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 22.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fonts,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fonts,
        fontSize = 11.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.5.sp
    )
)