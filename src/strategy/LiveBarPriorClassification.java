package strategy;

public class LiveBarPriorClassification {

    private double open, high, low, close;
    private int minute;
    private long volume;
    private int count;
    private double wap;
    private double tesla3, tesla6, tesla9;
    private String decision;

    public LiveBarPriorClassification(double open, double high, double low, double close, int minute, long volume, int count, double wap, double tesla3, double tesla6, double tesla9, String decision) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.minute = minute;
        this.volume = volume;
        this.count = count;
        this.wap = wap;
        this.tesla3 = tesla3;
        this.tesla6 = tesla6;
        this.tesla9 = tesla9;
        this.decision = decision;

    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
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
