package com.example.administrator.contactdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.contactdemo.R;
import com.example.administrator.contactdemo.contact.Phone;
import com.example.administrator.contactdemo.util.MyImageCache;
import com.example.administrator.contactdemo.util.UiUtil;
import com.example.administrator.contactdemo.widget.CircleImageView;

import java.util.List;

public class PhoneAdapter extends BaseAdapter {
    private Context context;
    private List<Phone> phoneList;
    private MyImageCache myCache;
    public PhoneAdapter(Context context, List<Phone> phoneList) {
        this.phoneList = phoneList;
        this.context = context;
    }

    public PhoneAdapter(Context context) {
        this.context = context;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public int getCount() {
        if (phoneList == null)
            return 0;
        return phoneList.size();
    }

    @Override
    public Object getItem(int position) {
        if (phoneList == null)
            return null;
        return phoneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (phoneList == null)
            return 0;
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.contact_item, null);
            viewHolder = new ViewHolder();
            viewHolder.contactItemName = (TextView) convertView.findViewById(R.id.contact_item_name);
            viewHolder.contactItemImage = (CircleImageView) convertView.findViewById(R.id.contact_item_image);
            viewHolder.contactItemNumberType = (TextView) convertView.findViewById(R.id.contact_item_number_type);
            viewHolder.contactItemPhoneNumber = (TextView) convertView.findViewById(R.id.contact_item_phone_number);
            viewHolder.tvGrucType = (TextView) convertView.findViewById(R.id.tv_gruc_type);
            viewHolder.contactItemState = (ImageView) convertView.findViewById(R.id.contact_item_state);
           convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Phone phone = phoneList.get(position);

        /*if (phone.getPhoto_thumbnail() != null){
            viewHolder.contactItemImage.setImageBitmap(getContactBitmap(position, phone));
        }*/
        viewHolder.contactItemName.setText(phone.getContactName());
        viewHolder.contactItemPhoneNumber.setText(phone.getCountryCode()+phone.getNumber());
        viewHolder.contactItemNumberType.setText(phone.getType()+" : ");
        switch (phone.getGrucType()){
            case Phone.GrucType.SYSTEM:
                viewHolder.tvGrucType.setText("invite");
                break;
            case Phone.GrucType.FRIEND:
                viewHolder.tvGrucType.setText("friend");
                break;
            case Phone.GrucType.GRUC:
                viewHolder.tvGrucType.setText("add");
                break;
        }
        return convertView;
    }
    private Bitmap defaultBitmap;

    /**
     * 获取
     * @param position
     * @param phone
     * @return
     */
    private Bitmap getContactBitmap(int position, Phone phone) {
        Bitmap bitmap = myCache.get(position);

        if (bitmap == null) {
            String photo_thumbnail = phone.getPhoto_thumbnail();

            if (photo_thumbnail != null) {
                try {
                    bitmap = BitmapFactory.decodeStream(
                            context.getContentResolver().openInputStream(Uri.parse(photo_thumbnail)));
                } catch (Exception e) {
                    bitmap = null;
                }
            }
            if (bitmap == null) {
                if(defaultBitmap == null || defaultBitmap.isRecycled()){
                    defaultBitmap = UiUtil.createDefaultBitmap(context,R.mipmap.contact_icon,50,50);
                }
                bitmap = defaultBitmap;
            } else {
                myCache.put(position, bitmap);
            }

        }
        return bitmap;
    }

    static class ViewHolder {
        CircleImageView contactItemImage;
        TextView contactItemName,tvGrucType;
        TextView contactItemNumberType;
        TextView contactItemPhoneNumber;
        ImageView contactItemState;

    }
}
