package abbosbek.mobiler.daggerhilt.viewModel

import abbosbek.mobiler.daggerhilt.models.ImageItem
import abbosbek.mobiler.daggerhilt.repository.ImageRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject
constructor(private val repository: ImageRepository) : ViewModel(){

    private val _response = MutableLiveData<List<ImageItem>>()
    val responseImage : LiveData<List<ImageItem>> get() = _response

    init {
        getAllImages()
    }

    private fun getAllImages() = viewModelScope.launch {
        repository.getAllImages().let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("My Error Bug", "getAllImages: ${response.errorBody()}")
            }
        }
    }

}