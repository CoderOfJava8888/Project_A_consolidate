package strategy;

import com.ib.client.Bar;
import java.util.ArrayList;

//  For classification
public class NewStrategy {

    double avgCurOpnPrvWAP;
    double tesla3, tesla6, tesla9;
    String signalDecision;
    String execution;
    int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;

    public String executionDeterminer(ArrayList<Bar> barInput) throws InterruptedException {  // throws InterruptedException {

        System.out.println("barInput.size()  " + barInput.size());
        if (barInput.size() == 13) {
            b12 = barInput.size() - 1;
            b11 = b12 - 1;
            b10 = b11 - 1;
            b9 = b10 - 1;
            b8 = b9 - 1;
            b7 = b8 - 1;
            b6 = b7 - 1;
            b5 = b6 - 1;
            b4 = b5 - 1;
            b3 = b4 - 1;
            b2 = b3 - 1;
            b1 = b2 - 1;
        }
        if (barInput.size() == 12) {
            b12 = 11;
            b11 = 10;
            b10 = 9;
            b9 = 8;
            b8 = 7;
            b7 = 6;
            b6 = 5;
            b5 = 4;
            b4 = 3;
            b3 = 2;
            b2 = 1;
            b1 = 0;
        }

        avgCurOpnPrvWAP = barInput.get(b12).wap() * barInput.get(b12).close();
        tesla3 = 0.3 * ((avgCurOpnPrvWAP - barInput.get(b1).wap()) + (avgCurOpnPrvWAP - barInput.get(b2).wap()) + (avgCurOpnPrvWAP - barInput.get(b3).wap()) + (avgCurOpnPrvWAP - barInput.get(b4).wap()));
        tesla6 = 0.6 * ((avgCurOpnPrvWAP - barInput.get(b5).wap()) + (avgCurOpnPrvWAP - barInput.get(b6).wap()) + (avgCurOpnPrvWAP - barInput.get(b7).wap()) + (avgCurOpnPrvWAP - barInput.get(b8).wap()));
        tesla9 = 0.9 * ((avgCurOpnPrvWAP - barInput.get(b9).wap()) + (avgCurOpnPrvWAP - barInput.get(b10).wap()) + (avgCurOpnPrvWAP - barInput.get(b11).wap()) + (avgCurOpnPrvWAP - barInput.get(b12).wap()));

        if (tesla3 < tesla6 && tesla6 < tesla9) {
            signalDecision = "BUY";
        }

        if (tesla3 > tesla6 && tesla6 > tesla9) {
            signalDecision = "SELL";
        }

        System.out.println("volume  " + barInput.get(b12).volume());
        System.out.println("count  " + barInput.get(b12).count());
        System.out.println("WAP  " + barInput.get(b12).wap());

        System.out.println("tesla3  " + tesla3);
        System.out.println("tesla6  " + tesla6);
        System.out.println("tesla9  " + tesla9);
        System.out.println("signal  " + signalDecision);

//        LiveBarPriorClassification test = new LiveBarPriorClassification(barInput.get(12).volume(), barInput.get(12).count(), barInput.get(12).wap(), tesla3, tesla6, tesla9, signalDecision);


//TEMPORARY -         
String executionOrNo = signalDecision;

        return executionOrNo;
    }

}
