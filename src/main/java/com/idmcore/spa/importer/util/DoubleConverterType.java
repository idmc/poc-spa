package com.idmcore.spa.importer.util;

import net.sf.jsefa.common.converter.SimpleTypeConverter;

/**
 * Converter used by jsefa to map input to double
 */
public class DoubleConverterType implements SimpleTypeConverter {

    private static final DoubleConverterType INSTANCE = new DoubleConverterType();

    private DoubleConverterType() {
    }

    public static DoubleConverterType create() {
        return INSTANCE;
    }

    @Override
    public Object fromString(String s) {
        if (s.contains(",")) {
            return new Double(s.replace(',', '.'));
        }
        return new Double(s);
    }

    @Override
    public String toString(Object d) {
        return d.toString();
    }

}