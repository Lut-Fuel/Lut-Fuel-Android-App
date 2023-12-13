package id.lutfuel.app.ui.postlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.api.ApiService
import id.lutfuel.app.data.model.response.PostItem
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    private val _state = MutableStateFlow<AsyncState<List<PostItem>>>(AsyncState.Loading())
    val state: StateFlow<AsyncState<List<PostItem>>> = _state


    suspend fun getPosts() {
        _state.value = AsyncState.Loading()
        val result = apiService.getPosts()
        _state.value = AsyncState.Success(result)
    }
}