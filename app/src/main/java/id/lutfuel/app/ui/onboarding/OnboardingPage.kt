package id.lutfuel.app.ui.onboarding

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.ui.destinations.BottomNavBarPageDestination
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun OnboardingPage(
    navigator: DestinationsNavigator,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val scope = rememberCoroutineScope()

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)
                    viewModel.handleGoogleSignInResult(task)
                }
            }
        }

    val isSignedIn by viewModel.isSignedIn.collectAsState()
    LaunchedEffect(isSignedIn) {
        if (isSignedIn) {
            navigator.popBackStack()
            navigator.navigate(BottomNavBarPageDestination())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        OnboardingAppBar(
            onSkip = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            },
            showSkip = pagerState.currentPage < pagerState.pageCount - 1
        )
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            OnboardingPager(pagerState)
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) LutFuelColor.primaryDefault else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)
                )
            }
        }
        if (pagerState.currentPage < pagerState.pageCount - 1)
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }

                }
            ) {
                Text(text = "Next")
            }
        else
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LutFuelColor.primaryWhite,
                    contentColor = Color.Black
                ),
                onClick = {
                    startForResult.launch(viewModel.googleSignInClient.signInIntent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Logo",
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Sign in with Google")
            }
    }
}

@Composable
fun OnboardingAppBar(
    onSkip: () -> Unit,
    showSkip: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.lutfuel_logo),
            contentDescription = "LutFuel Logo",
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = "LutFuel",
            modifier = Modifier
                .padding(start = 8.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        if (showSkip)
            TextButton(onClick = onSkip) {
                Text(text = "Skip")
            }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPager(
    pagerState: PagerState
) {

    HorizontalPager(
        state = pagerState
    ) { page ->
        when (page) {
            0 -> OnboardingPageItem(
                image = R.drawable.onboarding_1,
                title = "Ditch the gas price worries",
                description = "Lorem ipsum dolor sit amet consectetur. Vulputate auctor tincidunt viverra at. Suspendisse eget sit tempor interdum tristique in enim.",
            )

            1 -> OnboardingPageItem(
                image = R.drawable.onboarding_2,
                title = "Embrace the journey, not the hassle",
                description = "Lorem ipsum dolor sit amet consectetur. Vulputate auctor tincidunt viverra at. Suspendisse eget sit tempor interdum tristique in enim.",
            )

            2 -> OnboardingPageItem(
                image = R.drawable.onboarding_3,
                title = "Easy to use, even your grandma can figure it out",
                description = "Lorem ipsum dolor sit amet consectetur. Vulputate auctor tincidunt viverra at. Suspendisse eget sit tempor interdum tristique in enim.",
            )
        }
    }
}

@Composable
fun OnboardingPageItem(
    @DrawableRes image: Int,
    title: String,
    description: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "LutFuel Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF3F3D56),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = description,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFA9A9A9),
                textAlign = TextAlign.Center,
            )

        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPagePreview() {
    LutFuelTheme {
        OnboardingPage(EmptyDestinationsNavigator)
    }
}
