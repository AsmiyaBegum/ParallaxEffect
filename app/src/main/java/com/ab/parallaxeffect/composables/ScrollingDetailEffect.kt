package com.ab.parallaxeffect.composables

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.ab.parallaxeffect.R
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp // title minimum size
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 40.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ScrollEffect(
  
) {
    val snack = remember(String) { "Mushrooms are a diverse group of fungi that are known for their distinctive fruiting bodies, which are often referred to as \"mushrooms\" or \"toadstools.\" Here are some brief points about mushrooms:\n" +
            "\n" +
            "Fungus Classification: Mushrooms belong to the kingdom Fungi, a separate biological kingdom distinct from plants, animals, and bacteria.\n" +
            "\n" +
            "Fruiting Bodies: The visible part of a mushroom is its fruiting body, which typically consists of a cap (the top part) and a stalk. The cap is often the most recognizable part.\n" +
            "\n" +
            "Variety: There are thousands of mushroom species, and they come in a wide range of shapes, sizes, colors, and textures. Some are edible and delicious, while others are toxic and can be harmful if ingested.\n" +
            "\n" +
            "Ecological Role: Mushrooms play a crucial role in ecosystems as decomposers. They break down dead organic matter, helping to recycle nutrients back into the environment.\n" +
            "\n" +
            "Edibility: Many mushrooms are edible and are used in culinary dishes worldwide. Examples of edible mushrooms include button mushrooms, shiitake mushrooms, and portobello mushrooms.\n" +
            "\n" +
            "Toxicity: Some mushrooms are highly toxic and can cause severe illness or even be fatal if consumed. It's essential to be cautious when foraging for wild mushrooms and to rely on expert identification.\n" +
            "\n" +
            "Medicinal Use: Certain mushroom species have been used in traditional medicine for their potential health benefits. For example, reishi mushrooms are believed to have immune-boosting properties.\n" +
            "\n" +
            "Cultural Significance: Mushrooms have cultural significance in various societies. They are used in art, folklore, and religious rituals in different parts of the world.\n" +
            "\n" +
            "Cultivation: Edible mushrooms are often cultivated in controlled environments, such as mushroom farms. This allows for the production of consistent and safe mushroom varieties.\n" +
            "\n" +
            "Mycology: The scientific study of fungi, including mushrooms, is called mycology. Mycologists study their taxonomy, biology, ecology, and potential applications."
    }

    Box(
        Modifier
            .fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(snack, scroll)
        DetailImage() { scroll.value }
        GradientButton(text = "Book a test drive",textColor =  Color.White, gradient = Brush.linearGradient(
            listOf(Color.Blue, Color.Magenta, Color.Cyan)), modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
            })
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(Color.Blue, Color.Magenta, Color.Cyan)))
    )
}

@Composable
private fun Body(
    related: String,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = related,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                            .padding(horizontal = 24.dp)
                    )
                    
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(10.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun DetailImage(
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        Image(
           painterResource(id = R.drawable.ic_mushroom),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> kotlin.Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier.padding(10.dp),
        contentPadding = PaddingValues(),
        onClick = { onClick() })
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textColor)
        }
    }
}
