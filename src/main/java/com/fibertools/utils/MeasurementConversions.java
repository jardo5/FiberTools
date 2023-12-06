package com.fibertools.utils;

import java.text.DecimalFormat;

public class MeasurementConversions {

    //Kilometer to FT and simplify to 3 decimal places

    public static double KMtoFT(double km) {
        double feet = km * 3280.84;
        DecimalFormat df = new DecimalFormat("#.###");
        return Double.parseDouble(df.format(feet));
    }

    //Feet to KM
    public static double FTtoKM(double ft) {
        return ft * 0.0003048;
    }

}
