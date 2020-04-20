package com.example.istandwithlockdown;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    //testComment
    Context context=this;
    private Button b1,b2;
    private ImageButton b3;

    private  static final int RESULT_PICK_CONTACT=8777;

    private String name,address,item,message,quantity,contact;
    int currentitmID=6;
    int finditmID=6;

    int quantity_id = 1006;


    int i;

   TextView txt;
    EditText itm,qty;
    TableLayout t1;
    TableRow tr;
    int num=5; int srno=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = (Button) findViewById(R.id.add_row);
        b2 = (Button) findViewById(R.id.send_button);


        t1=(TableLayout) findViewById(R.id.table_layout);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            tr = new TableRow(MainActivity.this);
            txt = new TextView(MainActivity.this);
            itm = new EditText(MainActivity.this);
            qty = new EditText(MainActivity.this);

            num++;
            txt.setText(Integer.toString(num));
            txt.setGravity(Gravity.CENTER);
            txt.setTextColor(Color.BLACK);
//            txt.setId(currentsrnID);
//            currentsrnID++;
            txt.setTextSize(16);
            tr.addView(txt);


            itm.setHint("");
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
            itm.setLayoutParams(params);
            itm.setId(currentitmID);
            currentitmID++;
            tr.addView(itm);


            qty.setHint("");
            TableRow.LayoutParams paramsq = new TableRow.LayoutParams(
                        72, LayoutParams.MATCH_PARENT);
            qty.setLayoutParams(paramsq);
            qty.setId(quantity_id);
            quantity_id++;
            tr.addView(qty);


            t1.addView(tr);

            }
        });

        b3 = (ImageButton) findViewById(R.id.contactBtn) ;


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = (EditText)findViewById(R.id.person_name);

                name = ed.getText().toString();
                ed =(EditText) findViewById(R.id.person_address);
                address = ed.getText().toString();
                message = name + "\n" + address + "\n";

               EditText et; EditText et1;


                et = findViewById(R.id.item_1);
                if(!(et.getText().toString().isEmpty()))
                {

                   et1= findViewById(R.id.qty_1);
                    message = message + "\n" + srno + ". "  + et.getText().toString() + " - " + et1.getText().toString();
                    srno++;
                }



                et = findViewById(R.id.item_2);
                if(!(et.getText().toString().isEmpty()))
                {

                   et1= findViewById(R.id.qty_2);
                    message = message + "\n" + srno + ". "  + et.getText().toString() + " - " + et1.getText().toString();
                    srno++;
                }



                et = findViewById(R.id.item_3);
                if(!(et.getText().toString().isEmpty()))
                {

                   et1= findViewById(R.id.qty_3);
                    message = message + "\n" + srno + ". "  + et.getText().toString() + " - " + et1.getText().toString();
                    srno++;
                }



                et = findViewById(R.id.item_4);
                if(!(et.getText().toString().isEmpty()))
                {

                   et1= findViewById(R.id.qty_4);
                    message = message + "\n" + srno + ". "  + et.getText().toString() + " - " + et1.getText().toString();
                    srno++;
                }



                et = findViewById(R.id.item_5);
                if(!(et.getText().toString().isEmpty()))
                {

                   et1= findViewById(R.id.qty_5);
                    message = message + "\n" + srno + ". "  + et.getText().toString() + " - " + et1.getText().toString();
                    srno++;
                }




                //if input starts in 6th row loop will execute

                for(i=finditmID;i<currentitmID;i++)
                {


                    ed=(EditText) findViewById(i);
                    item =  ed.getText().toString();

//                    TextView txt_view=(TextView) findViewById(i+100);
//                    srn=txt_view.getText().toString();
                    if(!(item.isEmpty())){
                    EditText q = findViewById(i+1000);
                    quantity=q.getText().toString();
                    message = message + "\n" + srno + ". "  + item + " - " + quantity;
                    srno++;}


                }

//EXPLICIT INTENT
                EditText phn = findViewById(R.id.phone);

                contact = phn.getText().toString();
                String url = null;
                try {

                    url = "https://api.whatsapp.com/send?phone=91" + contact+"&text="+ URLEncoder.encode(message,"UTF-8");
                }

                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(url));
                sendIntent.putExtra(Intent.EXTRA_TEXT,message);
//            sendIntent.setPackage("com.whatsapp");

                startActivity(sendIntent);

                if (sendIntent.resolveActivity(getPackageManager()) == null) {
                    Toast.makeText(MainActivity.this, "Whatsapp not installed.", Toast.LENGTH_SHORT).show();

                }



                //IMPLICIT
//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                sendIntent.setType("text/plain");
//                sendIntent.putExtra("sms_body", message);
//                startActivity(sendIntent);


            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactIntent,RESULT_PICK_CONTACT);



            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    Cursor cursor = null;
                    try {
                        String phoneNo = null;

                        String editedPhone="";

                        Uri uri = data.getData();
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                        phoneNo = cursor.getString(phoneIndex);
                        phoneNo = phoneNo.replaceAll(" ","");
                        EditText phn = findViewById(R.id.phone);
                        if(phoneNo.charAt(0)=='+')
                        {
                            for(int i=3;i<phoneNo.length();i++)
                            {
                                editedPhone+= phoneNo.charAt(i);
                            }
                        }

                        else if(phoneNo.charAt(0)=='0')
                        {
                            for(int i=1;i<phoneNo.length();i++)
                            {
                                editedPhone+= phoneNo.charAt(i);
                            }
                        }
                        else if(phoneNo.length()== 10)
                        {
                            editedPhone = phoneNo;
                        }


                        phn.setText(editedPhone);




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Log.e("Failed", "Not able to pick contact");
        }
    }


}






