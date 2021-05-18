package com.jakewharton.sdksearch.api.dac

import com.jakewharton.sdksearch.proxy.model.DocumentedType

//const val PRODUCTION_PROXY = "https://api.sdksearch.app/"
const val PRODUCTION_PROXY = "http://0.0.0.0:8084/"

expect interface DocumentationService {
  suspend fun list(): List<DocumentedType>
}
