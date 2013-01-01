/*
 * Copyright (c) 2008 - 2012 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.mongodb;

import org.bson.types.Document;
import org.mongodb.annotations.Immutable;
import org.mongodb.serialization.PrimitiveSerializers;
import org.mongodb.serialization.Serializer;

@Immutable
public class MongoCollectionOptions extends MongoDatabaseOptions {
    public static Builder builder() {
        return new Builder();
    }

    public MongoCollectionOptions withDefaults(final MongoDatabaseOptions options) {
            Builder builder = new Builder();
            builder.primitiveSerializers = primitiveSerializers != null ? primitiveSerializers : options.getPrimitiveSerializers();
            builder.writeConcern = writeConcern != null ? writeConcern : options.getWriteConcern();
            builder.readPreference = readPreference != null ? readPreference : options.getReadPreference();
            builder.documentSerializer = documentSerializer != null ? documentSerializer : options.getDocumentSerializer();
            return builder.build();
    }

    public static class Builder extends MongoDatabaseOptions.Builder {
        public MongoCollectionOptions build() {
            return new MongoCollectionOptions(primitiveSerializers, writeConcern, readPreference, documentSerializer);
        }

        private Builder() {
        }
    }

    private MongoCollectionOptions(final PrimitiveSerializers primitiveSerializers, final WriteConcern writeConcern,
                                   final ReadPreference readPreference, final Serializer<Document> documentSerializer) {
        super(primitiveSerializers, writeConcern, readPreference, documentSerializer);
    }
}
