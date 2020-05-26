package org.example.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class Formatter{

        /**
         * @param array
         * @return
         */
        public static String Format(List<double[]> array) {

            List<String> strings = new ArrayList<String>();
            for (var entry : array) {
                strings.add(Arrays.toString(entry));
            }

            return strings.toString();
        };
    }


