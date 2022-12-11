package main;


import com.ib.client.Contract;

public class LiveContract {

    Contract contract;
    String symbol;
    double initialMargin;
    double maintMargin;
    int contract_ID;
    double PerTrade_Benefit;
    double PerTrade_Cost;
    int HistData_ReqID;
    int TickByTick_ReqID;
    
       public LiveContract(Contract contract, String symbol, int contract_ID, double initialMargin, double maintMargin, double PerTrade_Cost, double PerTrade_Benefit, int HistData_ReqID, int TickByTick_ReqID) {
        this.contract = contract;
        this.symbol = symbol;
        this.initialMargin = initialMargin;
        this.maintMargin=maintMargin;
        this.contract_ID = contract_ID;
        this.PerTrade_Cost=PerTrade_Cost;
        this.PerTrade_Benefit=PerTrade_Benefit;
        this.HistData_ReqID = HistData_ReqID;
        this.TickByTick_ReqID = TickByTick_ReqID;
    }

    public double getPerTrade_Benefit() {
        return PerTrade_Benefit;
    }

    public void setPerTrade_Benefit(double PerTrade_Benefit) {
        this.PerTrade_Benefit = PerTrade_Benefit;
    }

    public double getPerTrade_Cost() {
        return PerTrade_Cost;
    }

    public void setPerTrade_Cost(double PerTrade_Cost) {
        this.PerTrade_Cost = PerTrade_Cost;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getInitialMargin() {
        return initialMargin;
    }

    public void setInitialMargin(double initialMargin) {
        this.initialMargin = initialMargin;
    }

    public double getMaintMargin() {
        return maintMargin;
    }

    public void setMaintMargin(double maintMargin) {
        this.maintMargin = maintMargin;
    }

    public int getContract_ID() {
        return contract_ID;
    }

    public void setContract_ID(int contract_ID) {
        this.contract_ID = contract_ID;
    }


    public int getHistData_ReqID() {
        return HistData_ReqID;
    }

    public void setHistData_ReqID(int HistData_ReqID) {
        this.HistData_ReqID = HistData_ReqID;
    }

    public int getTickByTick_ReqID() {
        return TickByTick_ReqID;
    }

    public void setTickByTick_ReqID(int TickByTick_ReqID) {
        this.TickByTick_ReqID = TickByTick_ReqID;
    }
       
    
    
}

