package dev.hmh.jetpackcomposetoturial.scroll_recyclerview

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hmh.jetpackcomposetoturial.R
import kotlinx.coroutines.launch

@Composable
fun ScrollListTopToBottom() {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "scroll top to bottom",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(Color.White)
                    .wrapContentHeight(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(30) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(10.dp, 5.dp, 10.dp, 5.dp)
                            .background(Color.White),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.aa),
                                    contentDescription = "image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                Column {
                                    Text(
                                        text = "Android Developer ${index + 1}",
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(
                                        text = "Lorem Ipsum is Simply Item ${index + 1}",
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            val isTopBottomVisible = remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex > 0
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {
                    scope.launch {
                        if (isTopBottomVisible.value) {
                            listState.animateScrollToItem(0)
                        } else {
                            listState.animateScrollToItem(30)
                        }
                    }
                }
            ) {
                if (isTopBottomVisible.value){
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "up")
                }else{
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "down")
                }
            }
        }
    }
} 