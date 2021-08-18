package com.jakewharton.sdksearch.store.item

import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class InMemoryItemStoreTest {
  private val itemStore = InMemoryItemStore()

  @Test fun query() = runBlocking<Unit> {
    itemStore.updateItems(listOf(
      Item(-1, "com.example", "One", false, "one.html")
    ))
    assertEquals(1, itemStore.count().single())
    val expected = itemStore.queryItems("One").single()
    assertEquals(1, expected.size)
    val item = expected[0]
    assertEquals("com.example", item.packageName)
    assertEquals("One", item.className)
    assertFalse(item.deprecated)
    assertEquals("one.html", item.link)
  }
}
