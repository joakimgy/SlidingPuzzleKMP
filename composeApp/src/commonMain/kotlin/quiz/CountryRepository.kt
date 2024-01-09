import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class CountryRepository() {
    // https://restcountries.com/
    suspend fun getCountries(): List<Country> {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
        val response: HttpResponse = client.get("https://restcountries.com/v3.1/all?fields=name,flags,capital")
        println(response.status)
        client.close()
        return response.body<List<Country>>()
    }

}


@Serializable
data class Country(
    val flags: Flag,
    val name: Name,
    val capital: List<String>
)

@Serializable
data class Flag(
    val png: String,
    val svg: String,
    val alt: String
)

@Serializable
data class Name(
    val common: String,
    val official: String,
)