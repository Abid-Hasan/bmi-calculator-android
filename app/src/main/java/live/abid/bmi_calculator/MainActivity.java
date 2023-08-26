package live.abid.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText editTextAge;
    private EditText editTextFeet;
    private EditText editTextInches;
    private EditText editTextWeight;
    private Button buttonCalculate;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }


    private void findViews() {
        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_button_female);
        editTextAge = findViewById(R.id.edit_text_age);
        editTextFeet = findViewById(R.id.edit_text_feet);
        editTextInches = findViewById(R.id.edit_text_inches);
        editTextWeight = findViewById(R.id.edit_text_weight);
        buttonCalculate = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        buttonCalculate.setOnClickListener(view -> {
            try {
                double bmi = calculateBmi();
                showResult(bmi);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Please enter all the fields with valid values", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showResult(double bmi) {
        String result = "";
        double age = Double.parseDouble(editTextAge.getText().toString());
        if (age < 18) {
            String gender = "";
            if (radioButtonMale.isChecked()) gender = "male ";
            else if (radioButtonFemale.isChecked()) gender = "female ";
            result = "BMI is not applicable for " + gender + "child younger than 18. Please consult with your doctor.";
        } else {
            if (bmi < 18.5) {
                result = "Underweight";
            } else if (bmi >= 18.5 && bmi < 25) {
                result = "Healthy";
            } else if (bmi >= 25 && bmi < 30) {
                result = "Overweight";
            } else if (bmi >= 30) {
                result = "Obese";
            }
            result = "Your BMI is " + String.format(Locale.ENGLISH, "%.1f", bmi) + ". \nYou are " + result.toLowerCase(Locale.ENGLISH) + ".";
        }
        resultText.setText(result);
    }

    private double calculateBmi() {
        double feet = Double.parseDouble(editTextFeet.getText().toString());
        double inches = Double.parseDouble(editTextInches.getText().toString());
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double heightInMeter = ((feet * 12) + inches) * 0.0254;
        return weight / (heightInMeter * heightInMeter);
    }
}