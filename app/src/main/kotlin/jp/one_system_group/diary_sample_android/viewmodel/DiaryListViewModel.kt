package jp.one_system_group.diary_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    private val diaryList = MutableLiveData<List<DiaryRow>>()

    fun getDiaryList(): LiveData<List<DiaryRow>> {
        if (diaryList.value == null) {
            loadDiaryList()
        }
        return diaryList
    }

    private fun loadDiaryList() {
        // 非同期で日記の情報を読み込み、MutableLiveData に反映する
        viewModelScope.launch {
            diaryList.postValue(diaryRepository.getDiaryList().body())
        }
    }
}