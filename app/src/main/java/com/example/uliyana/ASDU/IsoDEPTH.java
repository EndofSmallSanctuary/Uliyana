package com.example.uliyana.ASDU;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;

public class IsoDEPTH {
    IsoDep isoProvider;
    private final String classicPPSE = "0E325041592E5359532E444446303100";

//TODO 1) Select Card's APDU AND ENTER IN WITH 07 LengthCOmmand
//TODO 2) AID IS AFTER 4F
    public  void sendTestCommand(Tag tag){
        byte[] APDUCommand = Utils.hexStringToByteArray(buildInitialRequestSignature(classicPPSE));


        isoProvider = IsoDep.get(tag);
        try {
            isoProvider.connect();
            byte[] result = isoProvider.transceive(APDUCommand);
            Log.d("dagos",Utils.bytesTOSIREN(result));
           APDUCommand = Utils.hexStringToByteArray(
                   buildInitialRequestSignature(retrieveAIDAvaliable(Utils.bytesTOSIREN(result))));
           result = isoProvider.transceive(APDUCommand);
            Log.d("dagos",Utils.bytesTOSIREN(result));
            result = isoProvider.transceive(Utils.hexStringToByteArray(buildEmptyPDOLSignature()));
            Log.d("dagos",Utils.bytesTOSIREN(result));
//            result = isoProvider.transceive(Utils.hexStringToByteArray(buildReadRequestSignature()));
//            Log.d("dagosRes",Utils.bytesTOSIREN(result));

        } catch (Exception e){
            Log.d("dagos",e.getMessage());
        }
    }

    private String buildInitialRequestSignature(String aid){
        String signature = "00A40400";
        return signature + aid;
    }

    private String buildReadRequestSignature(){
        String signature = "00B21008";
        return signature;
    }

    private String buildEmptyPDOLSignature(){
        String signature = "80A8000002830000";
        return signature;
    }

    private String retrieveAIDAvaliable(String PPSEResponce){
        String aid = "";
        for(int i=0; i<PPSEResponce.length()-1;i++){
            String substring = PPSEResponce.substring(i,i+2);
            if(substring.equals("4f")){
                Integer aidEstimatedLength = Integer.parseInt(PPSEResponce.substring(i+2,i+4));
                aid = PPSEResponce.substring(i+2,i+4+aidEstimatedLength*2);
            }
        }
        Log.d("dagos",aid);
        return aid;
    }


}
