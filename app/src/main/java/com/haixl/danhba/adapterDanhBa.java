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

    static class ViewHolder{
        private ImageView imgDb;
        private TextView txtName;
        private TextView txtPhone;
        protected ImageButton btnMenu;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
            viewHolder.imgDb=convertView.findViewById(R.id.itemAnh);
            viewHolder.txtName=convertView.findViewById(R.id.itemName);
            viewHolder.txtPhone=convertView.findViewById(R.id.itemPhone);
            viewHolder.btnMenu=convertView.findViewById(R.id.btn_menu);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.imgDb.setImageResource(danhBas.get(i).getIdAnh());
        viewHolder.txtName.setText(danhBas.get(i).getName());
        viewHolder.txtPhone.setText(danhBas.get(i).getPhone());
        viewHolder.btnMenu.setOnClickListener(new View.OnClickListener() {
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
                                View mView=inflater1.inflate(R.layout.custom_dialog_edit,null);
                                TextView txtName= mView.findViewById(R.id.edTen);
                                TextView txtSDT=mView.findViewById(R.id.edSdt);

                                txtName.setText(danhBas.get(i).getName());
                                txtSDT.setText(danhBas.get(i).getPhone());

                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setTitle(null);

                                builder1.setView(mView);
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

        return convertView;
    }
}
