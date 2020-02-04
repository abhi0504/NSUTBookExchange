package com.therealsanjeev.nsutbookexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class sell extends AppCompatActivity {

    FirebaseUser firebaseUser;

    //button & EditText
    Button btnReq;
    EditText etBookName;
    EditText etAuthor;
    EditText etPrice;
    EditText etSellerName;
    EditText etSellerNo;
    EditText etSellerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        btnReq=findViewById(R.id.btnReq);

        etBookName=findViewById(R.id.etBookName);
        etAuthor=findViewById(R.id.etAuthor);
        etPrice=findViewById(R.id.etPrice);
        etSellerName=findViewById(R.id.etSellerName);
        etSellerNo=findViewById(R.id.etSellerNo);
        etSellerEmail=findViewById(R.id.etSellerEmail);



        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input from user :
                String book = etBookName.getText().toString();
                String author = etAuthor.getText().toString();
                String price = etPrice.getText().toString();
                String sellerName = etSellerName.getText().toString();
                String sellerNo = etSellerNo.getText().toString();
                String sellerEmail = etSellerEmail.getText().toString();

                //add details on FireBase :
                DatabaseReference db =FirebaseDatabase.getInstance().getReference();
                if(book.length()!=0&&author.length()!=0&&price.length()!=0&&sellerName.length()!=0&&sellerNo.length()!=0){
                    User user = new User(book,author,price,sellerName,sellerEmail,sellerNo);
                    db.child("user").push().setValue(user);
                }else{
                    Toast.makeText(sell.this,"Invalid data!",Toast.LENGTH_LONG).show();
                }


                //remove after submit :

                etBookName.setText(null);
                etAuthor.setText(null);
                etPrice.setText(null);
                etSellerName.setText(null);
                etSellerNo.setText(null);
                etSellerEmail.setText(null);

                Toast.makeText(sell.this,"Your request Received, Thank You :)",Toast.LENGTH_LONG).show();
            }
        });

    }

}
