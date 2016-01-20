package whereabout.mrunal_sonal.com.testapp;

/**
 * Created by mrunal on 1/19/16.
 */import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class General_SelectMenu_WithPicsName_Adapter extends ArrayAdapter<String>
{
    private final Activity context;
    private final List<String> values;
    private final List<String> picNames;

    // holds information effectively and is resource conscious
    static class ViewHolder {
        public TextView menuName;
        public WebView menuIcon;

    }

    public General_SelectMenu_WithPicsName_Adapter(Activity context, List<String> objects, List<String> listOfnames) {
        super(context, R.layout.general_row_withpicture, objects);
        this.context = context;
        this.values = objects;
        this.picNames =listOfnames;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // makes sure the rowview hasn't already been used
        if (rowView == null) {
            // if it hasn't been used, it assigns the items for this row
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.general_row_withpicture, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.menuName = (TextView)rowView.findViewById(R.id.txtGeneralRowPic);
            viewHolder.menuIcon = (WebView)rowView.findViewById(R.id.imgGeneralRowPic);

            rowView.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder)rowView.getTag();
        String strMenuName = values.get(position);
        String lowerAndNoSpaces =picNames.get(position).toLowerCase(Locale.getDefault()).replace(" ", "_");

        int iconCode = context.getResources().getIdentifier(lowerAndNoSpaces, "drawable",
                context.getPackageName());

        // sets the values for this row
        holder.menuName.setText(strMenuName);
        holder.menuIcon.getSettings().setJavaScriptEnabled(true);
        holder.menuIcon.loadUrl(picNames.get(position));

        return rowView;
    }

}