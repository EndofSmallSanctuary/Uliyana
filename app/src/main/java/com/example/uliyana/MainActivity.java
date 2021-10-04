package com.example.uliyana;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.uliyana.ASDU.IsoDEPTH;
import com.example.uliyana.ASDU.Utils;
import com.example.uliyana.Notificator.NotificationManager;
import com.example.uliyana.Realm.RealmUtility;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import io.github.tapcard.android.NFCCardReader;
import io.github.tapcard.emvnfccard.model.EmvCard;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    NfcAdapter adapter;
    PendingIntent mPendingIntent;
    NFCCardReader nfcCardReader;
    NotificationManager notificationManager;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       notificationManager = new NotificationManager(this);

        //realm = Realm.getDefaultInstance();
        realm = Realm.getInstance(RealmUtility.getDefaultConfig());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        nfcCardReader = new NFCCardReader(this);


        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        nfcCardReader.enableDispatch();
        super.onResume();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        IsoDEPTH isoDEPTH = new IsoDEPTH();
        isoDEPTH.sendTestCommand(tag);
        Log.d("dagos",Utils.bytesToHex(tag.getId()));
//        if(nfcCardReader.isSuitableIntent(intent)){
//            try {
//
//                EmvCard emvCard = nfcCardReader.readCardBlocking(intent);
//                if(emvCard.getCardNumber()!=null) {
//
//
//
//
//                    RealmResults<RealmCard> matchingResult = realm.where(RealmCard.class).equalTo("cardnum",emvCard.getCardNumber()).findAll();
//                    if(matchingResult.size()>0){
//                        notificationManager.notify("Found matching card");
//                    }
//                    else {
//                        notificationManager.notify(emvCard.getCardNumber());
//                        realm.beginTransaction();
//
//                        RealmCard realmcard = realm.createObject(RealmCard.class, UUID.randomUUID());
//                        realmcard.setCardnum(emvCard.getCardNumber());
//                        if(emvCard.getExpireDate()!=null)
//                        realmcard.setExpdate(emvCard.getExpireDate().toString());
//                        realmcard.setAid(emvCard.getAid());
//                        if(emvCard.getType()!=null)
//                        realmcard.setCardtype(emvCard.getType().toString());
//                        realmcard.setHoldername(emvCard.getHolderFirstname()+" "+emvCard.getHolderLastname());
//                        realmcard.setTransactions(emvCard.getListTransactions().toString());
//
//                        realm.commitTransaction();
//                    }
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (NFCCardReader.WrongIntentException e) {
//                e.printStackTrace();
//            } catch (NFCCardReader.WrongTagTech wrongTagTech) {
//                wrongTagTech.printStackTrace();
//            }
//        }
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        nfcCardReader.disableDispatch();
       // adapter.disableForegroundDispatch(this);
        super.onPause();
    }
}