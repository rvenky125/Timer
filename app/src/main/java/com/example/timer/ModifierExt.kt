package com.example.timer

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

fun Modifier.mediaQuery(
    comparator: Dimensions.DimensionComparator,
    trueModifier: Modifier = Modifier,
    falseModifier: Modifier
): Modifier = composed {
    val screenWidth = with(LocalDensity.current) { LocalContext.current.resources.displayMetrics.widthPixels.toDp() }
    val screenHeight = with(LocalDensity.current) { LocalContext.current.resources.displayMetrics.heightPixels.toDp() }

    if (comparator.compare(screenWidth, screenHeight)) {
        this.then(trueModifier)
    } else this.then(falseModifier)
}