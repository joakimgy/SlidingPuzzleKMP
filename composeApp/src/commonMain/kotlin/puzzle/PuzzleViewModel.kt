package puzzle

import Country
import CountryRepository
import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.runBlocking

class PuzzleViewModel : StateScreenModel<PuzzleViewModel.State>(State.DefaultImage) {

    sealed class State {
        data object DefaultImage : State()
        data class GalleryImage(val image: ByteArray) : State()
    }

    fun setGalleryImage(image: ByteArray) {
        runBlocking {
            mutableState.value = State.GalleryImage(image)
        }
    }

    fun resetToDefaultImage() {
        runBlocking {
            mutableState.value = State.DefaultImage
        }
    }
}