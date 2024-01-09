import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun TopBar() {
    val navigator = LocalNavigator.currentOrThrow


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .padding(bottom = 1.dp).background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            Modifier.width(10.dp).background(MaterialTheme.colors.onError)
        )
        Button(
            enabled = navigator.canPop,
            onClick = { navigator.pop() }
        ) {
            Text("Go back")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Puzzles & Quiz",
                textAlign = TextAlign.Center,
            )
        }

    }
}