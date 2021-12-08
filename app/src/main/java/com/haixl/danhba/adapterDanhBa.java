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
        protected ImageButton btnDelete;
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
            viewHolder.btnDelete=convertView.findViewById(R.id.btn_delete);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.imgDb.setImageResource(danhBas.get(i).getIdAnh());
        viewHolder.txtName.setText(danhBas.get(i).getName());
        viewHolder.txtPhone.setText(danhBas.get(i).getPhone());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        return convertView;
    }
}
