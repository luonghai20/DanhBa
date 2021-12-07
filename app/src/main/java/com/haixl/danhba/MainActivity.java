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
        for (int i=0;i<10;i++){
            danhBas.add(new DanhBa(R.drawable.ic_baseline_people_24,"contact"+i,"100"+i));
        }


        adapterDanhBa=new adapterDanhBa(MainActivity.this,danhBas);
        lvDanhBa.setAdapter(adapterDanhBa);

        lvDanhBa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle=new Bundle();
                bundle.putInt("img",danhBas.get(i).getIdAnh());
                bundle.putString("name",danhBas.get(i).getName());
                bundle.putString("phone",danhBas.get(i).getPhone());
                intent.putExtras(bundle);
                startActivity(intent);

                return false;
            }
        });
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
                }else if (CheckVietHoa(edName.getText().toString())==false){
                    Toast.makeText(MainActivity.this,"Viết hoa chữ cái đầu",Toast.LENGTH_SHORT).show();
                }
                else if (edPhone.getText().toString().equals("")){
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

    public boolean CheckVietHoa(String str) {
        String str0=str.trim();
        str0 = str0.replaceAll("\\s+", " ");

        String temp[] = str0.split(" ");
        String str1="";
        for (int i=0;i<temp.length;i++){
            if (Character.isLowerCase(temp[i].charAt(0))){
                return false;
            }
        }
        return true;
    }

}