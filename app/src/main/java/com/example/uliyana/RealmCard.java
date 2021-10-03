package com.example.uliyana;

import java.util.UUID;

import io.github.tapcard.emvnfccard.model.EmvCard;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmCard extends RealmObject {

    @PrimaryKey
    UUID id;
    private String cardnum;
    private String cardtype;
    private String expdate;
    private String transactions;
    private String aid;


    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public RealmCard(){}


    public RealmCard(EmvCard emvCard){
       this.aid =  (emvCard.getAid()==null) ? null : emvCard.getAid();
       this.cardnum = (emvCard.getCardNumber()==null) ? null : emvCard.getCardNumber();
    }
}
