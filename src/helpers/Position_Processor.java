package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

///Margin quantity enforcer is a rule to maintain only ONE quantity position for any security.
///The method will thus limit by selling/buying any open quantity above absolute value ONE for margin considerations.

public class Position_Processor {

    int length, totalLength;
    String lastFourDigits;
    ArrayList<Double> rtnArrayList = new ArrayList<>();
    HashMap<String, Double> mapPositions = new HashMap<>();
    Double positionReturn;
    HashMap<String, Double> toEnforceQty = new HashMap<>();
    ArrayList<String> toOpenQty = new ArrayList<>();
    Double requiredQty;

    public HashMap<String, Double> marginQtyEnforcer(HashMap<String, Double> incomingPOSHashmap) throws InterruptedException {

        Set<HashMap.Entry<String, Double>> entrySet = incomingPOSHashmap.entrySet();
        for (HashMap.Entry<String, Double> currentEntry : entrySet) {
            String currentContract = currentEntry.getKey();
            double current_Position = currentEntry.getValue();

            if (Math.abs(current_Position) != 1.0) {
                if (current_Position < 0.0) {
                    double tempValue = Math.abs(current_Position);
                    requiredQty = Math.abs(1.0 - tempValue);
                    toEnforceQty.put(currentContract, requiredQty);  //Positive implies buy to reach margin qty
                }
                if (current_Position > 0.0) {
                    requiredQty = 1.0 - current_Position;
                    toEnforceQty.put(currentContract, requiredQty);    //Negative implies sell to reach margin qty
                }

            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Margin Qty Enforcer  " + currentContract + "  " + current_Position);
        }

        return toEnforceQty;
    }


    public ArrayList<String> zeroPostionDiscloser(HashMap<String, Double> incomingZPOSHashmap) throws InterruptedException {
toOpenQty.clear();
        Set<HashMap.Entry<String, Double>> entrySet = incomingZPOSHashmap.entrySet();
        for (HashMap.Entry<String, Double> currentEntry : entrySet) {
            String currentContract = currentEntry.getKey();
            double current_Position = currentEntry.getValue();

            if (Math.abs(current_Position) == 0.0) {
                toOpenQty.add(currentContract);
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Zero Qty To open  " + currentContract + "  " + current_Position);
        }
        return toOpenQty;
    }

    
    
    
    public double PositionDiscloser(Map<String, Double> passedHashMap, String inputSymbol) {
        for (Map.Entry<String, Double> entry : passedHashMap.entrySet()) {
            String key = entry.getKey();
            if (key == null ? inputSymbol == null : key.equals(inputSymbol)) {
                positionReturn = entry.getValue();
            }
        }
        return positionReturn;
    }
    
    public HashMap<String, Double> posArrayProcessor(ArrayList<String> incomingStrArray) {
        //ArrayList<Double> posArrayProcessor(ArrayList<String> incomingStrArray) {
        //   ArrayList<Entry<String, Double>> posArrayProcessor(ArrayList<String> incomingStrArray) {
        System.out.println("incoming arraylist size: " + incomingStrArray.size());
        Collections.sort(incomingStrArray);
        for (int p = 0; p < incomingStrArray.size(); p++) {
            incomingStrArray.get(p);
            totalLength = incomingStrArray.get(p).length();
            System.out.println("TotalLength " + totalLength);
            length = totalLength - 3;
            System.out.println("Length " + length);

            if (totalLength > 3) {
                lastFourDigits = incomingStrArray.get(p).substring(incomingStrArray.get(p).length() - length);
            } else {
                lastFourDigits = incomingStrArray.get(p);
            }
            String conSymbol = incomingStrArray.get(p).substring(0, 3);
            double dum = Double.parseDouble(lastFourDigits);
            mapPositions.put(conSymbol, dum);
            System.out.println(conSymbol + " Pos: " + dum);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        return mapPositions;

    }



    public double TotalAbsPositions_Discloser(HashMap<String, Double> incomingTreeMap) {

        double TotalAbsPositions = incomingTreeMap.values().stream()
                     .mapToDouble(w -> Math.abs(w))
                     .sum();

    return TotalAbsPositions;
}



}
