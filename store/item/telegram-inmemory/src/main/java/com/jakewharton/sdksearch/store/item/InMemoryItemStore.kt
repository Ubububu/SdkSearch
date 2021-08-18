package com.jakewharton.sdksearch.store.item

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class InMemoryItemStore : ItemStore {
  private var items = mutableListOf<Item>()
  override fun count(): Flow<Long> = flow {
    println("CHECK_CHECK")
    emit(items.size.toLong())
  }

  override suspend fun updateItems(items: List<Item>) {
    this.items = items.toMutableList()
  }

  override fun queryItems(term: String): Flow<List<Item>> = flow {
    emit(items.filter { it.className.contains(term, ignoreCase = true) }
      .sortedWith(compareBy {
        val name = it.className
        when {
          name.equals(term, ignoreCase = true) -> 1
          name.startsWith(term, ignoreCase = true) && name.indexOf('.') == -1 -> 2
          name.endsWith(".$term", ignoreCase = true) -> 3
          name.startsWith(term, ignoreCase = true) -> 4
          name.contains(".$term", ignoreCase = true) -> 5
          else -> 6
        }
      }))
  }
}
