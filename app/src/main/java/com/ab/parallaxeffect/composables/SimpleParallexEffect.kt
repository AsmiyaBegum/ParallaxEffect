package com.ab.parallaxeffect.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ab.parallaxeffect.R
import java.lang.Float.min


@Composable
fun FadeScrollEffect() {

    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .graphicsLayer {
                        alpha = min(1f, 1 - (scrollState.value / 450f))
                        translationY = -scrollState.value * 0.1f
                    }, contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mushroom),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            }

            Text(
                text = "Mushrooms are a diverse group of fungi that are known for their distinctive fruiting bodies, which are often referred to as \"mushrooms\" or \"toadstools.\" Here are some brief points about mushrooms:\n" +
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
                        "Mycology: The scientific study of fungi, including mushrooms, is called mycology. Mycologists study their taxonomy, biology, ecology, and potential applications.",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }

}

