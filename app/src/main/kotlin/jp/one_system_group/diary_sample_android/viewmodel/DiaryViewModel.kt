package jp.one_system_group.diary_sample_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.one_system_group.diary_sample_android.model.Diary
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: DiaryRepository,
): ViewModel() {
    var diary : Diary = Diary("","")

    fun getDiary(id : Int) {
        viewModelScope.launch {
            repository.getDiary(id).collect() {
                diary = it
            }
        }
    }
}