package helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio_Assessment {

    // Attempt to obtain a fixed gain or fixed loss amount per trade per contract
    
  //Map<String, List<Double>> portfolio_Positions = new HashMap<>();
    Map<String, List<Double>> criteria_Match = new HashMap<>();
    Map<String, Double> PositionsToClose = new HashMap<>();

    public Map<String, Double> portfolioAssessMethod(Map<String, List<Double>> incomingPortfolio) {

        
//        //  Narrow bandwidth for testing only
//       criteria_Match.put("AUD", Arrays.asList(-1.66, 2.60));
//        criteria_Match.put("JPY", Arrays.asList(-1.48, 2.67));
//        criteria_Match.put("CHF", Arrays.asList(-1.16, 2.6));
//        criteria_Match.put("GBP", Arrays.asList(-1.73, 2.9));
//        criteria_Match.put("EUR", Arrays.asList(-1.89, 2.0));



        criteria_Match.put("AUD", Arrays.asList(-46.66, 56.60));
        criteria_Match.put("JPY", Arrays.asList(-68.48, 79.67));
        criteria_Match.put("CHF", Arrays.asList(-85.16, 102.6));
        criteria_Match.put("GBP", Arrays.asList(-89.73, 100.9));
        criteria_Match.put("EUR", Arrays.asList(-90.89, 102.0));

        PositionsToClose.clear();
        
        for (Map.Entry<String, List<Double>> entry1 : incomingPortfolio.entrySet()) {
            String key = entry1.getKey();
            double value1_POS = entry1.getValue().get(0);
            double value1_mktPrice = entry1.getValue().get(1);
            double value1_mktValue = entry1.getValue().get(2);
            double value1_avgCost = entry1.getValue().get(3);
            double value1_uPNL = entry1.getValue().get(4);
            double value1_rPNL = entry1.getValue().get(5);

//            System.out.println();
//            System.out.println("Symbol           = " + key);
//            System.out.println("Position         = " + value1_POS);
//            System.out.println("Market_Price     = " + value1_mktPrice);
//            System.out.println("Market_Value     = " + value1_mktValue);
//            System.out.println("AverageCost      = " + value1_avgCost);
//            System.out.println("UnrealizedPNL    = " + value1_uPNL);
//            System.out.println("RealizedPNL      = " + value1_rPNL);
//
//            
            double value2_min = criteria_Match.get(key).get(0);
            double value2_max = criteria_Match.get(key).get(1);

//            System.out.println("Minimum          = " + value2_min);
//            System.out.println("Maximum          = " + value2_max);

            
            if (value1_uPNL/Math.abs(value1_POS) <= value2_min/Math.abs(value1_POS) || value1_uPNL/Math.abs(value1_POS) >= value2_max/Math.abs(value1_POS)) {
                PositionsToClose.put(key, value1_POS);
            }

        }

        System.out.println("Here's answer_Box:===> PositionsToClose =======================");
        System.out.println("Here's answer_Box:===>  " + PositionsToClose);
        System.out.println("Here's answer_Box:===> PositionsToClose =======================");

        return PositionsToClose;

    }
}
