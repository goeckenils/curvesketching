package org.example.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class Formatter{

        /**
         * @param array the list you want to format
         * @return it returns the list as a string
         */
        public static String Format(List<double[]> array) {

            List<String> strings = new ArrayList<String>();
            for (var entry : array) {
                strings.add(Arrays.toString(entry));
            }

            return strings.toString();
        };
    }


