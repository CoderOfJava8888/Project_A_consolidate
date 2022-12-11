/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */
package contracts;

import com.ib.client.Contract;

public class Contracts_202212_DEC {

    public static Contract AUD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("AUD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(299701779);
        return contract;
    }
    public static Contract CAD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("CAD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221220");
        contract.conid(300356971);
        return contract;
    }
    public static Contract CHF_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("CHF");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(299701893);
        return contract;
    }
    public static Contract EUR_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("EUR");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(299701833);
        return contract;
    }
    public static Contract GBP_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("GBP");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(299701782); 
        return contract;
    }
    public static Contract JPY_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("JPY");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(299701836);
        return contract;
    }
    public static Contract NZD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("NZD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(496647000);
        return contract;
    }
    public static Contract ZAR_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("ZAR");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(455429137);
        return contract;
    }

    
        public static Contract MXP_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("MXP");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");
        contract.conid(491421951);
        return contract;
    }


    Contract setContract(String symbol) {
        Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20221219");  //
        return contract;
    }

    Contract setContract(String symbol, String lastTradeOrMonth, String contract_ID) {
        Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth(lastTradeOrMonth);
        contract.conid(Integer.parseInt(contract_ID));   ///<<<--------------------------------------------
        return contract;
    }

    Contract setContract(String symbol, String lastTradeOrMonth, Integer contract_ID) {
        Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth(lastTradeOrMonth);
        contract.conid(contract_ID);
        return contract;
    }

    public static Contract FXFuture(String symbolIn, String currencyStringIn, String dateStringIn) {
        //! [futcontract]
        Contract contract = new Contract();
        contract.symbol(symbolIn);
        contract.secType("FUT");
        contract.currency(currencyStringIn);
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth(dateStringIn);
        //! [futcontract]
        return contract;
    }

}


/*

   https://interactivebrokers.github.io/tws-api/rtd_complex_syntax.html
LastTradeDateOrContractMonth	"exp="	Format 'YYYYMMDD' is used for defining the Last Trade Date, while format 'YYYYMM' is used for defining the Contract Month. 

 */
