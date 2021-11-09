package dev.hmh.jetpackcomposetoturial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import dev.hmh.jetpackcomposetoturial.expandable_card.ExpandableCard
import dev.hmh.jetpackcomposetoturial.search_widget.SearchWidget
import dev.hmh.jetpackcomposetoturial.search_widget.SearchWidgetViewModel
import dev.hmh.jetpackcomposetoturial.searchable.SearchAbleList
import dev.hmh.jetpackcomposetoturial.ui.theme.JetpackComposeToturialTheme

@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val viewModel: SearchWidgetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeToturialTheme {

                //SearchAbleList()
                //SwipeToReveal()
                //ExpandableCard()
                SearchWidget(viewModel =viewModel)
            }
        }
    }
}



