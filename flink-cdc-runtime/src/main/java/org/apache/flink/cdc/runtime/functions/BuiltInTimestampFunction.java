/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.cdc.runtime.functions;

import org.apache.flink.annotation.Internal;

import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.sql.SqlOperatorBinding;
import org.apache.calcite.sql.fun.SqlAbstractTimeFunction;
import org.apache.calcite.sql.type.SqlTypeName;

/**
 * Function that used to define SQL time function like LOCALTIMESTAMP, CURRENT_TIMESTAMP,
 * CURRENT_ROW_TIMESTAMP(), NOW() in Flink, the function support configuring the return type and the
 * precision of return type.
 */
@Internal
public class BuiltInTimestampFunction extends SqlAbstractTimeFunction {

    private final SqlTypeName returnTypeName;
    private final int precision;

    public BuiltInTimestampFunction(
            String functionName, SqlTypeName returnTypeName, int precision) {
        // access protected constructor
        super(functionName, returnTypeName);
        this.returnTypeName = returnTypeName;
        this.precision = precision;
    }

    @Override
    public RelDataType inferReturnType(SqlOperatorBinding opBinding) {
        return opBinding.getTypeFactory().createSqlType(returnTypeName, precision);
    }

    @Override
    public boolean isDeterministic() {
        return false;
    }
}
