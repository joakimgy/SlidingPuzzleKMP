package puzzle

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.runBlocking
import unsplash.UnsplashRepository

class PuzzleViewModel(private val unsplashRepository: UnsplashRepository) :
    StateScreenModel<PuzzleViewModel.State>(State.UnsplashImage("https://images.unsplash.com/photo-1702581783857-412fa1eb043f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1MzI5NTF8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MDQ4MTgxNzB8&ixlib=rb-4.0.3&q=80&w=1080")) {

    sealed class State(
        var points: Int = 0,
    ) {
        data object DefaultImage : State()
        data class GalleryImage(val image: ByteArray) : State()

        data class UnsplashImage(val url: String) : State()
    }

    fun updateUnsplashImage() {
        runBlocking {
            unsplashRepository.getImage().also {
                mutableState.value = State.UnsplashImage(it)
            }
        }
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
