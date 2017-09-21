/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lucio.mvp.kotlinmvp.util.retrofit.rxjava2

import retrofit2.Response

/** Exception for an unexpected, non-2xx HTTP response.  */
class HttpException(@Transient private val response: Response<*>) : Exception() {
    private fun getMessage(response: Response<*>?): String {
        if (response == null) throw NullPointerException("response == null")
        return "HTTP " + response.code() + " " + response.message()
    }

    private val code: Int
    private val messages: String

    init {
        this.code = response.code()
        this.messages = response.message()
    }

    /** HTTP status code.  */
    fun code(): Int {
        return code
    }

    /** HTTP status message.  */
    fun message(): String {
        return messages
    }

    /**
     * The full HTTP response. This may be null if the exception was serialized.
     */
    fun response(): Response<*> {
        return response
    }
}
