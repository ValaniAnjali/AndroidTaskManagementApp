package anjali.learning.taskmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PreferenceDetail extends AppCompatActivity {

EditText name;
String Name;
Switch darkmode;
Button Submit;
TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preference_detail);
        darkmode=findViewById(R.id.darkmode);
        name=findViewById(R.id.name);
        Submit=findViewById(R.id.submit);
        welcome=findViewById(R.id.welcome);
        SharedPreferences sp = getSharedPreferences("User_Pref",MODE_PRIVATE);

        welcome.setText("Welcome " + sp.getString("username", "Guest"));

        darkmode.setChecked(sp.getBoolean("isDarkMode",false));

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name=name.getText().toString();
                if(!Name.isEmpty()){
                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString("username",Name);
                    editor.putBoolean("isDarkMode",darkmode.isChecked());
                    editor.apply();
                    Toast.makeText(PreferenceDetail.this,"Settings saved successfully",Toast.LENGTH_SHORT).show();
                    Intent redirectToMain = new Intent(PreferenceDetail.this,MainActivity.class);
                    startActivity(redirectToMain);
                }else{
                    Toast.makeText(PreferenceDetail.this,"Please entername",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}