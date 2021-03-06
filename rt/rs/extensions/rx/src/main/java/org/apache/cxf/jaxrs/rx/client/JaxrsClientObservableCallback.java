/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.jaxrs.rx.client;

import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.apache.cxf.jaxrs.client.JaxrsClientCallback;

import rx.Observable;
import rx.schedulers.Schedulers;

public class JaxrsClientObservableCallback<T> extends JaxrsClientCallback<T> {
    private Observable<T> observable;

    public JaxrsClientObservableCallback(Class<?> responseClass,
                                  Type outGenericType,
                                  Executor ex) {
        super(null, responseClass, outGenericType);
        Future<T> f = super.createFuture();
        observable = ex == null ? Observable.from(f)
            : Observable.from(f, Schedulers.from(ex));
    }

    public Observable<T> getObservable() {
        return observable;
    }

}