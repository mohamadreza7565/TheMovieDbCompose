package com.mohammadreza.moviedbcompose.global

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
@Composable
private fun Int.sspGet(): Dp {

    val id = when (this) {
        in 1..600 -> "_${this}ssp"
        in (-60..-1) -> "_minus${this}ssp"
        else -> return this.dp
    }

    val resourceField = getFieldId(id)
    return if (resourceField != 0) dimensionResource(resourceField) else this.dp

}

@Composable
private fun getFieldId(id: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(id, "dimen", context.packageName)

}

val Int.ssp: Dp
    @Composable
    get() = this.sspGet()

@Composable
private fun Int.textssp(density: Density): TextUnit = with(density) {
    this@textssp.ssp.toSp()
}

val Int.textSsp: TextUnit
    @Composable get() = this.textssp(density = LocalDensity.current)
