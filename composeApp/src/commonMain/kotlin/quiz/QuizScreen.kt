import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import quiz.QuizViewModel


object QuizScreen : Screen {


    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { QuizViewModel(CountryRepository()) }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(currentCompositeKeyHash) {
            screenModel.getCountries()
        }

        when (val result = state) {
            is QuizViewModel.State.Init -> Text("Init")
            is QuizViewModel.State.Loading -> Text("Loading")
            is QuizViewModel.State.Result -> {
                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(result.countries) {
                        CountryCard(it)
                    }
                }
            }
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    // Scale the image to fit the width of a column.
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(country.flags.png)
                    .crossfade(true)
                    .build(),
                contentDescription = country.flags.alt,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(25.dp),
            )
            Spacer(Modifier.width(8.dp))
            Text(text = "${country.name.common} (${country.capital.firstOrNull() ?: "No capital"})")

        }
    }
}