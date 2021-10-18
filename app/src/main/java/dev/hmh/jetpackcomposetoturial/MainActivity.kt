package dev.hmh.jetpackcomposetoturial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import dev.hmh.jetpackcomposetoturial.expandable_card.ExpandableCard
import dev.hmh.jetpackcomposetoturial.searchable.SearchAbleList
import dev.hmh.jetpackcomposetoturial.ui.theme.JetpackComposeToturialTheme

@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeToturialTheme {

                //SearchAbleList()
                //SwipeToReveal()
                ExpandableCard()
            }
        }
    }
}



