package com.emagia.ach.achmaker;

import com.afrunt.jach.logic.ACHErrorMixIn;
import com.afrunt.jach.logic.ACHFieldConversionSupport;
import com.afrunt.jach.metadata.ACHMetadata;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import com.afrunt.jach.metadata.ACHMetadata;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

    /**
     * @author Anil Kadiga
     */
    class ACHProcessorUpdated implements ACHErrorMixIn, ACHFieldConversionSupport {
        static final String LINE_SEPARATOR = Optional.ofNullable(System.getProperty("line.separator")).orElse("\n");

        private final ACHMetadata metadata;
        private final Map<Integer, String> methodNamesCache = new HashMap<>();
        private Map<Integer, Method> methodsCache = new HashMap<>();

        @SuppressWarnings("WeakerAccess")
        public ACHProcessorUpdated(ACHMetadata metadata) {
            this.metadata = metadata;
        }

        ACHMetadata getMetadata() {
            return metadata;
        }

        @Override
        public Map<Integer, String> getMethodNamesCache() {
            return methodNamesCache;
        }

        @Override
        public Map<Integer, Method> getMethodsCache() {
            return methodsCache;
        }


    }