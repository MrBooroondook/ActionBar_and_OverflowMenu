package mr.booroondook.actionbar_and_overflowmenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private String[] lastNames;
    private ListView listView;

    private int ic_check_id_icon;
    private int adopted_id_string;
    private int ic_close_id_icon;
    private int fired_id_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastNames = getResources().getStringArray(R.array.last_names);
        listView = findViewById(R.id.list_view);

        updateAdapter();
    }

    private void updateAdapter() {
        ic_check_id_icon = R.drawable.ic_check_24dp;
        adopted_id_string = R.string.adopted;
        ic_close_id_icon = R.drawable.ic_close_24dp;
        fired_id_string = R.string.fired;
        listView.setAdapter(new CustomAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                updateAdapter();
                return true;
            case R.id.check:
                ic_check_id_icon = R.drawable.ic_check_24dp;
                adopted_id_string = R.string.adopted;
                ic_close_id_icon = R.drawable.ic_check_24dp;
                fired_id_string = R.string.adopted;
                listView.setAdapter(new CustomAdapter());
                return true;
            case R.id.adopted:
                ic_check_id_icon = R.drawable.ic_close_24dp;
                adopted_id_string = R.string.fired;
                ic_close_id_icon = R.drawable.ic_close_24dp;
                fired_id_string = R.string.fired;
                listView.setAdapter(new CustomAdapter());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter() {
            super(MainActivity.this, R.layout.list_cell, R.id.unit_name, lastNames);
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            if (viewHolder == null) {
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }

            if (Objects.requireNonNull(((CustomAdapter) listView.getAdapter())
                    .getItem(position)).length() % 2 == 0) {
                viewHolder.getIconStatus().setImageResource(ic_check_id_icon);
                viewHolder.getUnitStatus().setText(adopted_id_string );
            } else {
                viewHolder.getIconStatus().setImageResource(ic_close_id_icon );
                viewHolder.getUnitStatus().setText(fired_id_string );
            }
            return view;
        }
    }

    private static class ViewHolder {
        private final ImageView iconStatus;
        private final TextView unitStatus;

        ViewHolder(View view) {
            this.iconStatus = view.findViewById(R.id.icon_status);
            this.unitStatus = view.findViewById(R.id.unit_status);
        }

        ImageView getIconStatus() {
            return iconStatus;
        }

        TextView getUnitStatus() {
            return unitStatus;
        }
    }
}
