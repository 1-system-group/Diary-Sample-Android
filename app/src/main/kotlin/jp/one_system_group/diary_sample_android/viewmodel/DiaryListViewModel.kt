package jp.one_system_group.diary_sample_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.one_system_group.diary_sample_android.model.DiaryRow
import jp.one_system_group.diary_sample_android.repository.DiaryPagingSource
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    val diaryList : Flow<PagingData<DiaryRow>> = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 10, maxSize = 1000)
    ) {
        DiaryPagingSource(diaryRepository)
    }.flow
}