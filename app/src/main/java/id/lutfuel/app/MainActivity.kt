package id.lutfuel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.ui.NavGraphs

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LutFuelTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

}

//@RootNavGraph(start = true)
//@Destination
//@Composable
//fun Screen1(
//    navigator: DestinationsNavigator,
//) {
//    Column {
//        Text(text = "Screen 1")
//        Button(onClick = {
//            navigator.navigate(Screen2Destination())
//        }) {
//            Text(text = "Go to Screen 2")
//        }
//    }
//
//}
//
//@Destination
//@Composable
//fun Screen2(
//    navigator: DestinationsNavigator,
//) {
//    Column {
//        Text(text = "Screen 2")
//        Button(onClick = {
//            navigator.popBackStack()
//            navigator.navigate(Screen3Destination())
//        }) {
//            Text(text = "Go to Screen 3")
//
//        }
//    }
//
//}

//@Destination
//@Composable
//fun Screen3(
//    navigator: DestinationsNavigator,
//) {
//    Text(text = "Screen 3")
//}