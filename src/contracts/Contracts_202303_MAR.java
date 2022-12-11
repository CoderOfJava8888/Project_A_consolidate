/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */
package contracts;

import com.ib.client.Contract;

public class Contracts_202303_MAR {

    public static Contract AUD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("AUD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(310735312);
        return contract;
    }
    public static Contract CAD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("CAD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230314");
        contract.conid(311475769);
        return contract;
    }
    public static Contract CHF_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("CHF");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(310735327);
        return contract;
    }
    public static Contract EUR_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("EUR");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(310735266);
        return contract;
    }
    public static Contract GBP_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("GBP");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(310735351);
        return contract;
    }
    public static Contract JPY_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("JPY");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(310735300);
        return contract;
    }
    public static Contract MXP_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("MXP");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(508408218);
        return contract;
    }
    public static Contract NZD_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("NZD");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(513880701);
        return contract;
    }
    public static Contract RUR_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("RUR");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230315");
        contract.conid(310524606);
        return contract;
    }
    public static Contract ZAR_FXFutureContract() {
        Contract contract = new Contract();
        contract.symbol("ZAR");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");
        contract.conid(471017165);
        return contract;
    }


    Contract setContract(String symbol) {
        Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("CME");
        contract.lastTradeDateOrContractMonth("20230313");  //
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
