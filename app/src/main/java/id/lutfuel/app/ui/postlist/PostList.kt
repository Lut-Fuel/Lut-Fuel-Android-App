package id.lutfuel.app.ui.postlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import id.lutfuel.app.data.model.response.PostItem
import id.lutfuel.app.ui.shared.state.AsyncState

@Composable
fun PostList(
    modifier: Modifier = Modifier,
    viewModel: PostListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getPosts()
    }

    Scaffold { innerPadding ->
        when (state) {
            is AsyncState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
            is AsyncState.Error -> {
                val data = (state as AsyncState.Error<List<PostItem>>).exception
                Text(text = data.message ?: "Unknown error")
            }
            is AsyncState.Success -> {
                val data = (state as AsyncState.Success<List<PostItem>>).data
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    items(data.size) { index ->
                        PostListItem(
                            post = data[index],
                            onClick = {}
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: PostItem,
    onClick: () -> Unit
) {
    Column {
        Text(text = post.title)
        Text(text = post.body)
    }
}
