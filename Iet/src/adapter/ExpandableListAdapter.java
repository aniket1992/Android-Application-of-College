package adapter;

import java.util.List;
import java.util.Map;

import com.example.faq.FAQ;
import com.example.iet.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter{
	
	 private Map<String, List<String>> laptopCollections;
	private Activity context;
	private List<String> laptops;
	private LayoutInflater inflater;

	public ExpandableListAdapter(Activity faq, List<String> laptops, Map<String, List<String>> laptopCollections) {
	        this.context = faq;
	        this.laptopCollections = laptopCollections;
	        this.laptops = laptops;
	    }
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return laptopCollections.get(laptops.get(groupPosition)).get(childPosition);
		
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		 return childPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.faq_list,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.faq_parent);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

	public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }
 
    public int getGroupCount() {
        return laptops.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
	
	
	

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}









 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.faq_child, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.child);
 
  
 
        item.setText(laptop);
        return convertView;
    }

}
