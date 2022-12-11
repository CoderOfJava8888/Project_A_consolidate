package strategy;


public class LiveBarPriorClassification {
    private long volume;
    private int count;
    private double wap;
    private double tesla3, tesla6, tesla9;
    private String decision;

    
    
    public LiveBarPriorClassification(long volume, int count, double wap, double tesla3, double tesla6, double tesla9, String decision) {
    this.volume=volume;
    this.count=count;
    this.wap=wap;
    this.tesla3=tesla3;
    this.tesla6=tesla6;
    this.tesla9=tesla9;
    this.decision=decision;
    
    }


    
    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getWap() {
        return wap;
    }

    public void setWap(double wap) {
        this.wap = wap;
    }

    public double getTesla3() {
        return tesla3;
    }

    public void setTesla3(double tesla3) {
        this.tesla3 = tesla3;
    }

    public double getTesla6() {
        return tesla6;
    }

    public void setTesla6(double tesla6) {
        this.tesla6 = tesla6;
    }

    public double getTesla9() {
        return tesla9;
    }

    public void setTesla9(double tesla9) {
        this.tesla9 = tesla9;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
    
    




 

}