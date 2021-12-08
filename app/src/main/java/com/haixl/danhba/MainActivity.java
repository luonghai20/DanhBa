package com.haixl.danhba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText edName,edPhone;
    ListView lvDanhBa;
    ArrayList<DanhBa> danhBas;
    adapterDanhBa adapterDanhBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhone=findViewById(R.id.tvPhone);
        edName=findViewById(R.id.tvName);
        lvDanhBa=findViewById(R.id.lvContact);

        danhBas=new ArrayList<>();
        danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"Luong Xuan Hai","0768379308"));
        danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"Nguyen Hong Anh","0768379308"));
        danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"Nguyen Mai Dao","0768379308"));
        danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"Vi Manh Hung","0768379308"));
        danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"Hoang Tuan Khai","0768379308"));

        adapterDanhBa=new adapterDanhBa(MainActivity.this,danhBas);
        lvDanhBa.setAdapter(adapterDanhBa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_save:
                if (edName.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Tên không được trống",Toast.LENGTH_SHORT).show();
                } else if (edPhone.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"SDT không được trống",Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Lưu?");
                    builder.setMessage("Lưu thông tin này?");
                    builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int pos) {
                            danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,edName.getText().toString(),edPhone.getText().toString()));
                            adapterDanhBa.notifyDataSetChanged();
                            edName.setText("");
                            edPhone.setText("");
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int pos) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                    edName.clearFocus();
                    edPhone.clearFocus();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}