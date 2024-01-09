package unsplash

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import puzzle.sliding.SlidingPuzzle.BuildKonfig

class UnsplashRepository() {
    // https://restcountries.com/
    suspend fun getImage(): String {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
        val url = "https://api.unsplash.com/photos/random"
        val queryParams = "?client_id=${BuildKonfig.UNSPLASH_API}"
        val response: HttpResponse = client.get("$url$queryParams")
        client.close()
        return response.body<UnleashResponse>().urls.regular
    }

}


@Serializable
data class UnleashResponse(
    val urls: Urls,
    val user: User,
    @SerialName("alt_description")
    val altDescription: String
)

@Serializable
data class Urls(
    val regular: String,
)

@Serializable
data class User(
    val name: String,
)
