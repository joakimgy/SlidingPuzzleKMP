import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
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
            is QuizViewModel.State.Result -> LazyColumn(
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

@Composable
fun CountryCard(country: Country) {
    Text(
        text = "${country.name.common} (${country.capital.firstOrNull() ?: "No capital"})",
        modifier = Modifier
            .padding(16.dp),
    )
}