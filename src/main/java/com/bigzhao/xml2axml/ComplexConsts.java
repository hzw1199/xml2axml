package com.bigzhao.xml2axml;

/**
 * Created by Roy on 15-10-5.
 */
public class ComplexConsts {
    // Where the unit type information is.  This gives us 16 possible
    // types, as defined below.
    public static final int UNIT_SHIFT = 0;
    public static final int UNIT_MASK = 0xf;

    // TYPE_DIMENSION: Value is raw pixels.
    public static final int UNIT_PX = 0;
    // TYPE_DIMENSION: Value is Device Independent Pixels.
    public static final int UNIT_DIP = 1;
    // TYPE_DIMENSION: Value is a Scaled device independent Pixels.
    public static final int UNIT_SP = 2;
    // TYPE_DIMENSION: Value is in points.
    public static final int UNIT_PT = 3;
    // TYPE_DIMENSION: Value is in inches.
    public static final int UNIT_IN = 4;
    // TYPE_DIMENSION: Value is in millimeters.
    public static final int UNIT_MM = 5;

    // TYPE_FRACTION: A basic fraction of the overall size.
    public static final int UNIT_FRACTION = 0;
    // TYPE_FRACTION: A fraction of the parent size.
    public static final int UNIT_FRACTION_PARENT = 1;

    // Where the radix information is, telling where the decimal place
    // appears in the mantissa.  This give us 4 possible fixed point
    // representations as defined below.
    public static final int RADIX_SHIFT = 4;
    public static final int RADIX_MASK = 0x3;

    // The mantissa is an integral number -- i.e., 0xnnnnnn.0
    public static final int RADIX_23p0 = 0;
    // The mantissa magnitude is 16 bits -- i.e, 0xnnnn.nn
    public static final int RADIX_16p7 = 1;
    // The mantissa magnitude is 8 bits -- i.e, 0xnn.nnnn
    public static final int RADIX_8p15 = 2;
    // The mantissa magnitude is 0 bits -- i.e, 0x0.nnnnnn
    public static final int RADIX_0p23 = 3;

    // Where the actual value is.  This gives us 23 bits of
    // precision.  The top bit is the sign.
    public static final int MANTISSA_SHIFT = 8;
    public static final int MANTISSA_MASK = 0xffffff;
}
