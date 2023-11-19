/*
 * Copyright 2023 Ververica Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ververica.cdc.common.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.ververica.cdc.common.annotation.PublicEvolving;
import com.ververica.cdc.common.event.Event;

/**
 * {@code FlinkSourceFunctionProvider} is used to provide a Flink {@link SourceFunction} for reading
 * events from external systems.
 */
@PublicEvolving
public interface FlinkSourceFunctionProvider extends EventSourceProvider {

    /** Get the {@link SourceFunction} for reading events from external systems. */
    SourceFunction<Event> getSourceFunction();

    /** Create a {@link FlinkSourceFunctionProvider} from a {@link SourceFunction}. */
    static FlinkSourceFunctionProvider of(SourceFunction<Event> sourceFunction) {
        return () -> sourceFunction;
    }
}