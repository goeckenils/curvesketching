package org.example.Helper;

import javafx.scene.control.Button;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class Formatter{

        /**
         * @param array the list you want to format
         * @return it returns the list as a string
         */
        public static String StringFormat(List<double[]> array) {

            List<String> strings = new ArrayList<String>();
            for (var entry : array) {
                strings.add(Arrays.toString(entry));
            }

            return strings.toString();
        };

        public static String DecimalFormat (double number) {
            DecimalFormat formatter = new DecimalFormat("#.####");
            return formatter.format(number);
        }

        public static void SetState (List<double[]> list, javafx.scene.text.Text Content, javafx.scene.text.Text Title, Button Clipboard) {
            if(list.isEmpty()) {
                Clipboard.setDisable(true);
                Content.setText("Not available");
                Title.setStyle("-fx-fill:  #D6E1E6");
                Content.setStyle("-fx-fill:  #D6E1E6");

            } else {

                String List = "";

                for (var entry: list) {

                    List += DecimalFormat(entry[0]) +"/"+ DecimalFormat(entry[1]);
                    List = list.size() > 1 ? List += "  " : List;

                }

                Content.setText(List);
            }
        }
    }


