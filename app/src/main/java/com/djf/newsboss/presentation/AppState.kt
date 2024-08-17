package com.djf.newsboss.presentation

import com.djf.newsboss.util.APILatestResultItem


enum class Screen {
    NEWS, CRYPTO
}

sealed class ScreenState(val screen: Screen) {
    class NewsScreen(screen: Screen = Screen.NEWS) : ScreenState(screen)
    class CryptoNewsScreen(screen: Screen = Screen.CRYPTO) : ScreenState(screen)

}


data class AppState(
    val screenState: ScreenState,
    val list : List<APILatestResultItem>
)