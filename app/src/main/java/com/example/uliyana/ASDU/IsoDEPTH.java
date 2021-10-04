package com.example.uliyana.ASDU;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;

public class IsoDEPTH {
    IsoDep isoProvider;

//TODO 1) Select Card's APDU AND ENTER IN WITH 07 LengthCOmmand
    public  void sendTestCommand(Tag tag){
        byte[] APDUCommand = Utils.hexStringToByteArray("00A404000E325041592E5359532E444446303100");


        isoProvider = IsoDep.get(tag);
        try {
            isoProvider.connect();
            byte[] result = isoProvider.transceive(APDUCommand);
            Log.d("dagos",Utils.bytesTOSIREN(result));
        } catch (Exception e){
            Log.d("dagos",e.getMessage());
        }
    }


}
