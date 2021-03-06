package dev.hmh.jetpackcomposetoturial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import dev.hmh.jetpackcomposetoturial.constraint_layout.ConstraintLayout
import dev.hmh.jetpackcomposetoturial.expandable_card.ExpandableCard
import dev.hmh.jetpackcomposetoturial.scroll_recyclerview.ScrollListTopToBottom
import dev.hmh.jetpackcomposetoturial.search_widget.SearchWidget
import dev.hmh.jetpackcomposetoturial.search_widget.SearchWidgetViewModel
import dev.hmh.jetpackcomposetoturial.searchable.SearchAbleList
import dev.hmh.jetpackcomposetoturial.shemmer_effect.AnimatedShimmer
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
                //SearchWidget(viewModel =viewModel)
                //ScrollListTopToBottom()
                //ConstraintLayout()



                    Column {
                        repeat(10) {
                            AnimatedShimmer()
                        }
                    }

            }
        }
    }
}



