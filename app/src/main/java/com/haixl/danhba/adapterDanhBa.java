package com.haixl.danhba;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class adapterDanhBa extends BaseAdapter {
    Context context;
    ArrayList<DanhBa> danhBas;

    public adapterDanhBa(Context context, ArrayList<DanhBa> danhBas) {
        this.context = context;
        this.danhBas = danhBas;
    }

    @Override
    public int getCount() {
        return danhBas.size();
    }

    @Override
    public Object getItem(int i) {
        return danhBas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.item_listview,viewGroup,false);

        ImageView imgDb=view.findViewById(R.id.itemAnh);
        TextView txtName=view.findViewById(R.id.itemName);
        TextView txtPhone=view.findViewById(R.id.itemPhone);
        ImageButton btnMenu=view.findViewById(R.id.btn_menu);

        imgDb.setImageResource(danhBas.get(i).getIdAnh());
        txtName.setText(danhBas.get(i).getName());
        txtPhone.setText(danhBas.get(i).getPhone());
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                               //Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Xóa?");
                                builder.setMessage("Xóa thông tin này?");
                                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int pos) {
                                        danhBas.remove(i);
                                        notifyDataSetChanged();

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
                                return true;

                            case R.id.update:
                               // Toast.makeText(context,"update",Toast.LENGTH_SHORT).show();
                                LayoutInflater inflater1=LayoutInflater.from(context);
                                View mview=inflater1.inflate(R.layout.custom_dialog_edit,null);
                                TextView txtName= mview.findViewById(R.id.edTen);
                                TextView txtSDT=mview.findViewById(R.id.edSdt);

                                txtName.setText(danhBas.get(i).getName());
                                txtSDT.setText(danhBas.get(i).getPhone());

                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setTitle(null);

                                builder1.setView(mview);
                                builder1.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int pos) {
                                        DanhBa danhBa=new DanhBa(R.drawable.ic_baseline_people_24,txtName.getText().toString(),txtSDT.getText().toString());
                                        danhBas.set(i,danhBa);
                                        notifyDataSetChanged();
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder1.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int pos) {
                                        dialogInterface.dismiss();
                                    }
                                });


                                AlertDialog alertDialog1=builder1.create();
                                alertDialog1.show();
                                return true;
                        }

                        return false;
                    }

                });
                popupMenu.show();
            }
        });

        return view;
    }
}
