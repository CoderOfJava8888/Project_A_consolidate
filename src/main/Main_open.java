package main;

/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable.
   version98501_IBKR_Implementation
 */
import helpers.Portfolio_Assessment;
import helpers.Position_Processor;
import java.util.*;
import com.ib.client.*;
import java.util.concurrent.TimeUnit;
import contracts.*;
import strategy.NewStrategy;

public class Main_open {

    public static void main(String[] args) throws InterruptedException {

        //TimeUnit.SECONDS.sleep(60);  // 60 second pause when incorporating Windows Task Scheduler to activate IbcAlpha https://github.com/IbcAlpha/IBC
        //TimeUnit.SECONDS.sleep(30);  // 30 second pause when incorporating Windows Task Scheduler to activate IbcAlpha https://github.com/IbcAlpha/IBC
        EWrapper_Implementation wrapper = new EWrapper_Implementation();
        NewStrategy newStrategy = new NewStrategy();
        Portfolio_Assessment portfolioAssessment = new Portfolio_Assessment();
        Position_Processor positions = new Position_Processor();       //  methods marginQtyEnforcer, ZeroPositionDiscloser, PositionDiscloser, posArrayProcessor

        final EClientSocket m_client = wrapper.getClient();
        final EReaderSignal m_signal = wrapper.getSignal();
        //! [connect]
        m_client.eConnect("127.0.0.1", 7497, 0);
        //! [connect]
        //! [ereader]
        final EReader reader = new EReader(m_client, m_signal);

//  Ports
//  7497  paper-trading account - TWS
//  7496  live account - TWS
//  4002  paper-trading account - IB Gateway
//  4000  live account  - IB Gateway
        reader.start();
        //An additional thread is created in this program design to empty the messaging queue
        new Thread(() -> {
            while (m_client.isConnected()) {
                m_signal.waitForSignal();
                try {
                    reader.processMsgs();
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }).start();
        //! [ereader]
        // A pause to give the application time to establish the connection
        // In a production application, it would be best to wait for callbacks to confirm the connection is complete
        Thread.sleep(1000);

        LiveContract liveContract_00 = new LiveContract(Contracts_202303_MAR.AUD_FXFutureContract(), "AUD", 310735312, 2800.00, 2240.00, 27.00, 22.00, 3002, 19002);
        LiveContract liveContract_01 = new LiveContract(Contracts_202303_MAR.CHF_FXFutureContract(), "CHF", 310735327, 5110.00, 4088.00, 45.00, 40.00, 3006, 19006);
        LiveContract liveContract_02 = new LiveContract(Contracts_202303_MAR.EUR_FXFutureContract(), "EUR", 310735266, 5454.00, 4363.00, 47.00, 42.00, 3008, 19008);
        LiveContract liveContract_03 = new LiveContract(Contracts_202303_MAR.GBP_FXFutureContract(), "GBP", 310735351, 5384.00, 4308.00, 47.00, 42.00, 3007, 19007);
        LiveContract liveContract_04 = new LiveContract(Contracts_202303_MAR.JPY_FXFutureContract(), "JPY", 310735300, 4110.00, 3288.00, 37.00, 32.00, 3005, 19005);
                
        
        Map<String, LiveContract> portfolioVault = new HashMap<>();
        portfolioVault.put("AUD", liveContract_00);
        portfolioVault.put("CHF", liveContract_01);
        portfolioVault.put("EUR", liveContract_02);
        portfolioVault.put("GBP", liveContract_03);
        portfolioVault.put("JPY", liveContract_04);
        
        
        
                ArrayList<LiveContract> activeContracts = new ArrayList<>();
        activeContracts.add(portfolioVault.put("AUD", liveContract_00));
        activeContracts.add(portfolioVault.put("CHF", liveContract_01));
        activeContracts.add(portfolioVault.put("EUR", liveContract_02));
        activeContracts.add(portfolioVault.put("GBP", liveContract_03));
        activeContracts.add(portfolioVault.put("JPY", liveContract_04));

        OrderPlacer OOP = new OrderPlacer(wrapper.getClient(), wrapper.getCurrentOrderId());

        int x = 1;
        do {

            marketDataType(wrapper.getClient());

            //tickByTickOperations(wrapper.getClient());
            //tickDataOperations(wrapper.getClient());
            //tickOptionComputations(wrapper.getClient());
            //optionsOperations(wrapper.getClient());
            //orderOperations(wrapper.getClient(), wrapper.getCurrentOrderId());
            //contractOperations(wrapper.getClient());
            //hedgeSample(wrapper.getClient(), wrapper.getCurrentOrderId());
            //testAlgoSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
            //bracketSample(wrapper.getClient(), wrapper.getCurrentOrderId());
            //bulletins(wrapper.getClient());
            //fundamentals(wrapper.getClient());
            //marketScanners(wrapper.getClient());
            //marketDataType(wrapper.getClient());
            //historicalDataRequests(wrapper.getClient());
////////********************************************************************************************************   
////// /*We always want to start with this because whenever coming from a weekend or an extended period of no activity, the position and portfolio callback method will return empty */
            System.out.println("Initial start | Pristine start | ");
            positionStatusOperations(wrapper.getClient());
            wrapper.getPositions();
            updatePortfolioOperations(wrapper.getClient());
            wrapper.getHashMap_UpdatePortfolio();

            TimeUnit.SECONDS.sleep(3);

            if (wrapper.getHashMap_UpdatePortfolio().size() != 0 || wrapper.getPositions().isEmpty()) {

                if (positions.TotalAbsPositions_Discloser(wrapper.getPositions()) == 0.00) {

                    System.out.println("BOTH Portfolio and Positions are empty or null ==>Positons  size:  " + wrapper.getPositions().size());
                    System.out.println("BOTH Portfolio and Positions are empty or null ==>Portfolio size:  " + wrapper.getHashMap_UpdatePortfolio().size());
                    System.out.println("BOTH Portfolio and Positions are empty or null");

                    for (int cr = 0; cr < activeContracts.size(); cr++) {
                        Contract currentContract = activeContracts.get(cr).getContract();
                        String currentSymbolFUT = activeContracts.get(cr).getSymbol();
                        int reqID_HistoricalData = activeContracts.get(cr).getHistData_ReqID();
                        TimeUnit.SECONDS.sleep(3);
                        historicalDataMainOPERATIONS(reqID_HistoricalData, wrapper.getClient(), currentContract);
                        wrapper.getBarsHistDataArrayList();

                        newStrategy.executionDeterminer(currentSymbolFUT, wrapper.getBarsHistDataArrayList());
                        String commandBs = newStrategy.executionDeterminer(currentSymbolFUT,wrapper.getBarsHistDataArrayList());

                        Thread.sleep(10000);
                        TimeUnit.SECONDS.sleep(5);

                        if ("SELL".equals(commandBs)) {
                            OOP.placeOrder(currentContract, OrderTypes.MarketOrder("SELL", 1));
                        }
                        if ("BUY".equals(commandBs)) {
                            OOP.placeOrder(currentContract, OrderTypes.MarketOrder("BUY", 1));
                        }

                        TimeUnit.SECONDS.sleep(5);
                    }
                }
            }
////    //********************************************************************************************************   
            /*Here at this point, we assume full capacity meaning 5 contracts are open--so we assess if unRealizedPNL meets our criteria for closing position.*/

            System.out.println("'Full' Capacity if eligible | Portfolio Assessment | unRealizedPNL actualization");

            updatePortfolioOperations(wrapper.getClient());
            wrapper.getHashMap_UpdatePortfolio();
            portfolioAssessment.portfolioAssessMethod(wrapper.getHashMap_UpdatePortfolio());
            Map<String, Double> PositionsToActualize = portfolioAssessment.portfolioAssessMethod(wrapper.getHashMap_UpdatePortfolio());
            System.out.println("Here's answer_Box:===>  " + PositionsToActualize);

            Set<Map.Entry<String, Double>> entrySet = PositionsToActualize.entrySet();
            for (Map.Entry<String, Double> currentEntry : entrySet) {

                String key = currentEntry.getKey();
                double assess_Position = currentEntry.getValue();
                Double newData0 = assess_Position;
                int integerAssess = newData0.intValue();
                Contract currentContract = portfolioVault.get(key).getContract();

                System.out.println("String_Symbol          " + key);
                System.out.println("OPEN Position          " + assess_Position);
                System.out.println("OPEN Position Symbol   " + currentContract.symbol());
                System.out.println("          ");
                Thread.sleep(10000);
                TimeUnit.SECONDS.sleep(5);

                if (assess_Position > 0.0) {
                    OOP.placeOrder(currentContract, OrderTypes.MarketOrder("SELL", integerAssess));
                }
                if (assess_Position < 0.0) {
                    OOP.placeOrder(currentContract, OrderTypes.MarketOrder("BUY", Math.abs(integerAssess)));
                }
                TimeUnit.SECONDS.sleep(8);
            }
            PositionsToActualize.clear();

//------- This portion of code opens positions if zero positions exist.
            System.out.println("Above/BelowCapacity - Zero positions checker/OPENER  ");

            positionStatusOperations(wrapper.getClient());
            wrapper.getPositions();
            ArrayList<String> toOpen = new ArrayList<>();
            toOpen = positions.zeroPostionDiscloser(wrapper.getPositions());
            System.out.println("Number of postions to open= " + toOpen.size() + " Positions needed to open: " + toOpen);
            for (int cr = 0; cr < toOpen.size(); cr++) {
                String strContract = toOpen.get(cr);
                //Set<HashMap.Entry<String, LiveContract>> entrySet = portfolioVault.entrySet();
                // for (HashMap.Entry<String, LiveContract> currentEntry : entrySet) {
                Contract currentContract = portfolioVault.get(strContract).getContract();
                int reqID_HistoricalData = portfolioVault.get(strContract).getHistData_ReqID();
                TimeUnit.SECONDS.sleep(3);
                historicalDataMainOPERATIONS(reqID_HistoricalData, wrapper.getClient(), currentContract);
                TimeUnit.SECONDS.sleep(5);
                if (wrapper.getBarsHistDataArrayList().size() != 0) {
                    TimeUnit.SECONDS.sleep(5);
                    ArrayList<Bar> incomingBarInput = wrapper.getBarsHistDataArrayList();
                    String determinedDecision = newStrategy.executionDeterminer(strContract, incomingBarInput);
                    TimeUnit.SECONDS.sleep(4);
                    if ("BUY".equals(determinedDecision) || "SELL".equals(determinedDecision)) {
                        OOP.placeOrder(currentContract, OrderTypes.MarketOrder(determinedDecision, 1));
                    }
                    incomingBarInput.clear();
                }
                // }
            }

//------- END OF ...This portion of code opens positions if zero positions exist.
////********************************************************************************************************   
//////Here at this point, code to enforce limit on number of open positions due to margin requirement  - begin
/////////////code to enforce margin quantity  - begin
            System.out.println("Above/BelowCapacity | Margin considerations | Margin qty enforcer ");
            positionStatusOperations(wrapper.getClient());
            wrapper.getPositions();
            HashMap<String, Double> currPostionsHashMap = new HashMap<>();
            currPostionsHashMap = wrapper.getPositions();
            System.out.println(currPostionsHashMap);
            positions.marginQtyEnforcer(currPostionsHashMap);
            HashMap<String, Double> EnforceToClose = new HashMap<>();
            EnforceToClose = positions.marginQtyEnforcer(currPostionsHashMap);
            System.out.println("Here's to Enforce Qty " + EnforceToClose);

            Set<Map.Entry<String, Double>> entrySetToEnforce = EnforceToClose.entrySet();
            for (Map.Entry<String, Double> currentEntry : entrySetToEnforce) {

                String key = currentEntry.getKey();
                double current_Position = currentEntry.getValue();
                Double newData = current_Position;
                int integerValue = newData.intValue();
                Contract currentContract = portfolioVault.get(key).getContract();

                System.out.println("To enforce String_Symbol              " + key);
                System.out.println("To enforce Current Position (double)  " + current_Position);
                System.out.println("To enforce Current Position (integer) " + integerValue);
                System.out.println("To enforce Current Position Symbol    " + currentContract.symbol());
                System.out.println("          ");
                Thread.sleep(10000);
                TimeUnit.SECONDS.sleep(5);

                if (current_Position > 0.0) {
                    OOP.placeOrder(currentContract, OrderTypes.MarketOrder("BUY", integerValue));
                }
                if (current_Position < 0.0) {
                    OOP.placeOrder(currentContract, OrderTypes.MarketOrder("SELL", Math.abs(integerValue)));

                }
                TimeUnit.SECONDS.sleep(8);
            }
            EnforceToClose.clear();

/////////////end - code to enforce margin quantity
            System.out.println("          ");
            System.out.println("          ");
            System.out.println("Timer   x =       " + x);
            System.out.println("          ");
            System.out.println("          ");

            TimeUnit.MINUTES.sleep(1);

        } while (x == 1);

        System.out.println();
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("  === Open completed === Open completed === Open completed === Open completed === ");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println();

        //accountOperations(wrapper.getClient());
        //newsOperations(wrapper.getClient());
        //marketDepthOperations(wrapper.getClient());
        //rerouteCFDOperations(wrapper.getClient());
        //marketRuleOperations(wrapper.getClient());
        //tickDataOperations(wrapper.getClient());
        //pnlSingle(wrapper.getClient());
        //continuousFuturesOperations(wrapper.getClient());
        //pnlSingle(wrapper.getClient());
        //histogram(wrapper.getClient());
        //whatIfSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
        //historicalTicks(wrapper.getClient());
        System.out.println();
        System.out.println(" End == End == End == End == End == End == End == End == End == End == End == End == End == End ");
        System.out.println();
        TimeUnit.SECONDS.sleep(5);

    }

    private static void updatePortfolioOperations(EClientSocket client) throws InterruptedException {
        client.reqAccountUpdates(true, "DU999999");
        //! [reqaaccountupdates]
        Thread.sleep(5000);  //5 seconds
        //! [cancelaaccountupdates]
        client.reqAccountUpdates(false, "DU999999");
        //! [cancelaaccountupdates]
        Thread.sleep(1000);
    }

    private static void positionStatusOperations(EClientSocket client) throws InterruptedException {
        client.reqPositions();
        TimeUnit.SECONDS.sleep(5);
        client.cancelPositions();
    }

    public static void historicalDataMainOPERATIONS(int reqID_HistData, EClientSocket client, Contract FXFuture00000) throws InterruptedException {
        System.out.println();
        System.out.println("Futures Historical Data Main-- OPERATION    --->  historicalDataMainOPERATIONS    ***   ---    " + FXFuture00000.description() + "  ");
//      This portion of code below is needed--TagValue a custom class. Although the broker IBKR has not matured the development, yet still needed.
        List<TagValue> chartOptions = new ArrayList<TagValue>();
        for (TagValue chartOption : chartOptions) {
            System.out.println("Car name is: " + chartOption.m_tag);
        }
        client.reqHistoricalData(reqID_HistData, FXFuture00000, "", "3600 s", "5 mins", "TRADES", 0, 1, false, chartOptions);

//1.5hrs=90min =5400seconds
//2hrs=120min =7200seconds
//void reqHistoricalData(int tickerId, Contract contract, string endDateTime, string durationStr, string barSizeSetting, string whatToShow,
//		int useRTH, int formatDate, bool keepUpToDate, List< TagValue > chartOptions )
        /*

15 minutes = 900 seconds
20 minutes = 1200 seconds
25 minutes = 1500 seconds

30 minutes = 1800 seconds
35 minutes = 2100 seconds
40 minutes = 2400 seconds
45 minutes = 2700 seconds
60 minutes = 3600 seconds

TRADES
MIDPOINT
BID
ASK
BID_ASK
        
void reqHistoricalData(int tickerId,
		Contract contract,
		string endDateTime,
		string durationStr,
		string barSizeSetting,
		string whatToShow,
		int useRTH,
		int formatDate,
		bool keepUpToDate,
		List< TagValue > chartOptions 
	)

client.reqHistoricalData(4001, ContractSamples, formatted, "1 M", "1 day", "MIDPOINT", 1, 1, false, null);
client.reqHistoricalData(tickerId, contract, endDateTime, durationStr, barSizeSetting, whatToShow, useRTH, formatDate,keepUpToDate, List<TagValue>chartOptions); 

         */
 /* Error. Id: 4001, Code: 321, Msg: Error validating request:-'bI' : cause - Historical data bar size setting is invalid. 
        Legal ones are: 1 secs, 5 secs, 10 secs, 15 secs, 30 secs, 1 min, 2 mins, 3 mins, 5 mins, 10 mins, 15 mins, 20 mins, 30 mins, 1 hour, 2 hours, 3 hours, 4 hours, 8 hours, 1 day, 1W, 1M
         */
        Thread.sleep(4000);
        client.cancelHistoricalData(reqID_HistData);

        System.out.println("");
        System.out.println("");
    }

    private static void marketDataType(EClientSocket client) {
        //! [reqmarketdatatype]
        /**
         * * Switch to live (1) frozen (2) delayed (3) or delayed frozen (4)**
         */
        client.reqMarketDataType(1);
        //! [reqmarketdatatype]

    }

    private static void contractOperations(EClientSocket client) {
        //! [reqcontractdetails]
        client.reqContractDetails(211, Contracts_202212_DEC.FXFuture("AUD", "USD", "20221219"));
        client.reqContractDetails(212, Contracts_202212_DEC.FXFuture("CHF", "USD", "20221219"));
        client.reqContractDetails(213, Contracts_202212_DEC.FXFuture("EUR", "USD", "20221219"));
        client.reqContractDetails(214, Contracts_202212_DEC.FXFuture("GBP", "USD", "20221219"));
        client.reqContractDetails(215, Contracts_202212_DEC.FXFuture("JPY", "USD", "20221219"));
        client.reqContractDetails(216, Contracts_202212_DEC.FXFuture("MXP", "USD", "20221219"));
        client.reqContractDetails(217, Contracts_202212_DEC.FXFuture("NZD", "USD", "20221219"));
        client.reqContractDetails(218, Contracts_202212_DEC.FXFuture("BRE", "USD", "20221219"));
        client.reqContractDetails(219, Contracts_202212_DEC.FXFuture("ZAR", "USD", "20221219"));
        client.reqContractDetails(220, Contracts_202212_DEC.FXFuture("RUR", "USD", "202212"));
        client.reqContractDetails(221, Contracts_202212_DEC.FXFuture("CAD", "USD", "202212"));
        //! [reqcontractdetails]
//        //! [reqmatchingsymbols]
//        client.reqMatchingSymbols(211, "IB");
//        //! [reqmatchingsymbols]
    }

    private static void TickByTickOperations(EClientSocket client, int request_ID, Contract tickSide01234) throws InterruptedException {
        /**
         * * Requesting tick-by-tick data (only refresh) **
         */
        //! [reqtickbytick]
        client.reqTickByTickData(request_ID, tickSide01234, "Last", 0, false);
        //  client.reqTickByTickData(19002, ContractSamples.JpyFXFuture(), "AllLast", 0, false);
        //  client.reqTickByTickData(19003, ContractSamples.JpyFXFuture(), "BidAsk", 0, true);
        //  client.reqTickByTickData(19004, ContractSamples.JpyFXFuture(), "MidPoint", 0, false);
        //! [reqtickbytick]
        TimeUnit.SECONDS.sleep(5);

        //! [canceltickbytick]
        client.cancelTickByTickData(request_ID);
        //  client.cancelTickByTickData(19002);
        //  client.cancelTickByTickData(19003);
        //  client.cancelTickByTickData(19004);

        //void reqTickByTickData( int  requestId, Contract  contract, string  tickType, int  numberOfTicks, bool  ignoreSize) 
        //https://interactivebrokers.github.io/tws-api/classIBApi_1_1EClient.html#a3ab310450f1261accd706f69766b2263
        //! [canceltickbytick]
    }

//    private static void pnl(EClientSocket client) throws InterruptedException {
//        //! [reqpnl]
//        client.reqPnL(17001, "DU999999", "");
//        //! [reqpnl]
//        Thread.sleep(1000);
//        //! [cancelpnl]
//        client.cancelPnL(17001);
//        //! [cancelpnl]
//    }
//    private static void pnlSingle(EClientSocket client) throws InterruptedException {
//        //! [reqpnlsingle]
//        client.reqPnLSingle(17001, "DU999999", "", 268084);
//        //! [reqpnlsingle]
//        Thread.sleep(1000);
//        //! [cancelpnlsingle]
//        client.cancelPnLSingle(17001);
//        //! [cancelpnlsingle]
//    }
}
