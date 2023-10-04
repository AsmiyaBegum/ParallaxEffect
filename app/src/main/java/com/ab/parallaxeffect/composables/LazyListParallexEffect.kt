package com.ab.parallaxeffect.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ab.parallaxeffect.R
import java.lang.Float.min


@Composable
fun LazyListParallaxEffect() {
    val lazyListState = rememberLazyListState()
    val firstItemTranslationY by remember {
        derivedStateOf {
            if (lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0) {
                lazyListState.firstVisibleItemScrollOffset * 0.0f
            } else {
                0f
            }
        }
    }
    val firstItemVisibility by remember {
        derivedStateOf {
            if (lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() && lazyListState.firstVisibleItemIndex == 0) {
                1 - lazyListState.firstVisibleItemScrollOffset.toFloat() / lazyListState.layoutInfo.visibleItemsInfo[0].size
            } else {
                1f
            }
        }
    }

    LazyColumn(state = lazyListState) {
        item {
            Image(
                painter = painterResource(R.drawable.ic_mushroom),
                contentDescription = "Mushroom",
                modifier = Modifier
                    .aspectRatio(16 /12f)
                    .graphicsLayer {
                        alpha = firstItemVisibility
                        translationY = firstItemTranslationY
                    },
                contentScale = ContentScale.Crop
            )
        }
        val itemsa = List(50) { index -> "Mushroom ${index + 1}" }.toList()
        items(itemsa){ item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp)
            ) {
               Text(text = item,  Modifier.padding(10.dp))
                Divider()
            }
        }
    }
}
