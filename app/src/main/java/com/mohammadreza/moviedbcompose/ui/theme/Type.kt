package com.mohammadreza.moviedbcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Fonts = FontFamily(
    Font(regularFont),
    Font(regularFont, FontWeight.Normal),
    Font(boldFont,FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(regularFont)),


    /*
   body1 = TextStyle(
       fontFamily = FontFamily(Font(boldFont)),
       fontWeight = FontWeight.Normal,
       fontSize = 16.sp
   )

     Other default text styles to override
     button = TextStyle(
         fontFamily = FontFamily.Default,
         fontWeight = FontWeight.W500,
         fontSize = 14.sp
     ),
     caption = TextStyle(
         fontFamily = FontFamily.Default,
         fontWeight = FontWeight.Normal,
         fontSize = 12.sp
     )
     */
)