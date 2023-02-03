package strategy;

import com.ib.client.Bar;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//  For classification
public class NewStrategy {

    double avgCurOpnPrvWAP;
    double tesla3, tesla6, tesla9;
    String signalDecision;
    String execution;
    int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
    
    private static final DecimalFormat dft = new DecimalFormat("###.###########");
    
    public String executionDeterminer(String currentSymbolFUT, ArrayList<Bar> barInput) throws InterruptedException {  // throws InterruptedException {
        Instant instant = Instant.now();
        int min = instant.atZone(ZoneOffset.UTC).getMinute();
        int minute=min;
        
        if (min >= 0 && min <= 2) {
            minute = 0;
        }
        if (min >= 58 && min <= 59) {
            minute = 0;
        }
        if (min >= 19 && min <= 22) {
            minute = 20;
        }
        if (min >= 39 && min <= 42) {
            minute = 40;
        }
        if((minute!=20 && minute!=40)&&(minute!=0)){minute=min;}
        
        
        System.out.println("min =   " + min + "    Minute " + minute);
        System.out.println("barInput.size()  " + barInput.size());

        if (barInput.size() >= 12) {
        
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

            avgCurOpnPrvWAP = (barInput.get(b12).wap() + barInput.get(b12).close())/2.0;
            tesla3 = 0.3 * ((avgCurOpnPrvWAP - barInput.get(b1).wap()) + (avgCurOpnPrvWAP - barInput.get(b2).wap()) + (avgCurOpnPrvWAP - barInput.get(b3).wap()) + (avgCurOpnPrvWAP - barInput.get(b4).wap()));
            tesla6 = 0.6 * ((avgCurOpnPrvWAP - barInput.get(b5).wap()) + (avgCurOpnPrvWAP - barInput.get(b6).wap()) + (avgCurOpnPrvWAP - barInput.get(b7).wap()) + (avgCurOpnPrvWAP - barInput.get(b8).wap()));
            tesla9 = 0.9 * ((avgCurOpnPrvWAP - barInput.get(b9).wap()) + (avgCurOpnPrvWAP - barInput.get(b10).wap()) + (avgCurOpnPrvWAP - barInput.get(b11).wap()) + (avgCurOpnPrvWAP - barInput.get(b12).wap()));

            if (tesla3 < tesla6 && tesla6 < tesla9) {
                signalDecision = "BUY";
            }

            if (tesla3 > tesla6 && tesla6 > tesla9) {
                signalDecision = "SELL";
            }

            TimeUnit.SECONDS.sleep(3);
            System.out.println(" " );
            System.out.println("barInput.get(b12).wap()  = "+barInput.get(b12).wap());
            System.out.println("barInput.get(b12).close()= "+barInput.get(b12).close());
            System.out.println("avgCurOpnPrvWAP          = " +avgCurOpnPrvWAP);
            System.out.println(" " );
            System.out.println("volume  " + barInput.get(b12).volume());
            System.out.println("count  " + barInput.get(b12).count());
            System.out.println("WAP  " + barInput.get(b12).wap());
            System.out.println("minute  " + minute);
            System.out.println(" " );
            System.out.println("tesla3         " + dft.format(tesla3));
            System.out.println("tesla6         " + dft.format(tesla6));
            System.out.println("tesla9         " + dft.format(tesla9));
            System.out.println("signalDecision " + signalDecision);

//        LiveBarPriorClassification test = new LiveBarPriorClassification(barInput.get(12).volume(), barInput.get(12).count(), barInput.get(12).wap(), tesla3, tesla6, tesla9, signalDecision);
//TEMPORARY -         
            
 

        }

//TEMPORARY -    ERASE/EDIT BELOW
//  GOAL IS TO OBTAIN EXECUTE OR NO RETURN FROM MACHINE LEARNING PIECE.
        String executionOrNo = signalDecision;  //  <--- This is temporary.  Need link here to ML piece

        return executionOrNo;

    }


    
    
    
}
