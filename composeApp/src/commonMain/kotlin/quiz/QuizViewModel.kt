package quiz

import Country
import CountryRepository
import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.runBlocking

class QuizViewModel(
    private val repository: CountryRepository
) : StateScreenModel<QuizViewModel.State>(State.Init) {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class Result(val countries: List<Country>) : State()
    }

    fun getCountries() {
        runBlocking {
            mutableState.value = State.Loading
            mutableState.value = State.Result(countries = repository.getCountries())
        }
    }
}
