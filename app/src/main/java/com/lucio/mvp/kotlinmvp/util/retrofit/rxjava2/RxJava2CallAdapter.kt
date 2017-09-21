/*
 * Copyright (C) 2016 Jake Wharton
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

import java.lang.reflect.Type

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response

internal class RxJava2CallAdapter(private val responseType: Type, private val scheduler: Scheduler?, private val isResult: Boolean, private val isBody: Boolean,
                                  private val isFlowable: Boolean, private val isSingle: Boolean, private val isMaybe: Boolean, private val isCompletable: Boolean) : CallAdapter<Any, Any> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<Any>): Any {
        val responseObservable = CallObservable(call)

        var observable: Observable<*>
        if (isResult) {
            observable = ResultObservable(responseObservable)
        } else if (isBody) {
            observable = BodyObservable(responseObservable)
        } else {
            observable = responseObservable
        }

        if (scheduler != null) {
            observable = observable.subscribeOn(scheduler)
        }

        if (isFlowable) {
            return observable.toFlowable(BackpressureStrategy.LATEST)
        }
        if (isSingle) {
            return observable.singleOrError()
        }
        if (isMaybe) {
            return observable.singleElement()
        }
        if (isCompletable) {
            return observable.ignoreElements()
        }
        return observable
    }

    //    @Override
    //    public <R> Object adapt(Call<R> call) {
    //        Observable<Response<R>> responseObservable = new CallObservable<>(call);
    //
    //        Observable<?> observable;
    //        if (isResult) {
    //            observable = new ResultObservable<>(responseObservable);
    //        } else if (isBody) {
    //            observable = new BodyObservable<>(responseObservable);
    //        } else {
    //            observable = responseObservable;
    //        }
    //
    //        if (scheduler != null) {
    //            observable = observable.subscribeOn(scheduler);
    //        }
    //
    //        if (isFlowable) {
    //            return observable.toFlowable(BackpressureStrategy.LATEST);
    //        }
    //        if (isSingle) {
    //            return observable.singleOrError();
    //        }
    //        if (isMaybe) {
    //            return observable.singleElement();
    //        }
    //        if (isCompletable) {
    //            return observable.ignoreElements();
    //        }
    //        return observable;
    //    }
}
