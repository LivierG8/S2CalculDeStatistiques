package com.univ.l3.s2_calculdestatistiques;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.univ.l3.s2_calculdestatistiques.Statistiques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    public String statNbCaracteres, statNbMots, statMotPlusGrand, statMotPlusCourt, statMotPlusFrequent, statNbMiniOccurences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EditText chaineSaisie = (EditText) findViewById(R.id.chaineSaisie);
        chaineSaisie.addTextChangedListener(mTextEditorWatcher);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        String statNbCaracteres, statNbMots, statMotPlusGrand, statMotPlusCourt, statMotPlusFrequent, statNbMiniOccurences;

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String st = (String) s;

            this.statNbCaracteres = String.valueOf(st.length());

            String[] mots = st.split(" ");
            this.statNbMots = String.valueOf(mots.length);

            //String motPlusLong = " ";
            //String motPlusCourt = "anticonstitutionnellement";
            /*for (int i = 0; i < mots.length; i++)
                for (int j = 1 + i; j < mots.length; j++) {
                    if (mots[j].length() >= mots[i].length())
                        motPlusLong = mots[j];
                    if (mots[j].length() <= mots[i].length())
                        motPlusCourt = mots[j];
                }*/
            String motPlusLong = Arrays.stream(mots).max(Comparator.comparing(String::length)).orElse(null);
            String motPlusCourt = Arrays.stream(mots).min(Comparator.comparing(String::length)).orElse(null);
            this.statMotPlusGrand = motPlusLong + " (" + motPlusLong.length() + " caractères)";
            this.statMotPlusCourt = motPlusCourt + " (" + motPlusCourt.length() + " caractères)";

            String motPlusFrequent = "";
            int comptePlus = 0;
            int compteMini = 0;
            int compteBoucle;
            for (int i = 0; i < mots.length; i++) {
                String motEnCours = mots[i];
                compteBoucle = (int) Arrays.stream(mots).filter(mot -> motEnCours.equals(mot)).count();
                /*for (int j = 0; j < mots.length; j++) {
                    if (mots[i].equals(mots[j]))
                        compteBoucle ++;
                }*/
                if (compteBoucle > comptePlus) {
                    comptePlus = compteBoucle;
                    motPlusFrequent = mots[i] + " (" + String.valueOf(comptePlus) + " occurences)";
                }
                if (compteBoucle < compteMini) {
                    compteMini = compteBoucle;
                }
            }
            this.statMotPlusFrequent = motPlusFrequent;
            this.statNbMiniOccurences = String.valueOf(compteMini);

            Statistiques.valTailleChaine = this.statNbCaracteres;
            Statistiques.valNbMots = this.statNbMots;
            Statistiques.valMotPlusGrand = this.statMotPlusGrand;
            Statistiques.valMotPlusCourt = this.statMotPlusCourt;
            Statistiques.valMotPlusFrequent = this.statMotPlusFrequent;
            Statistiques.valNbMiniOccurences = this.statNbMiniOccurences;
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculStatistiques(View v) {
        
        EditText chaineSaisie = (EditText) v.findViewById(R.id.chaineSaisie);
        String st = (String) chaineSaisie.getText().toString();

        this.statNbCaracteres = String.valueOf(st.length());

        String[] mots = st.split(" ");
        this.statNbMots = String.valueOf(mots.length);

        String motPlusLong = Arrays.stream(mots).max(Comparator.comparing(String::length)).orElse(null);
        String motPlusCourt = Arrays.stream(mots).min(Comparator.comparing(String::length)).orElse(null);
        this.statMotPlusGrand = motPlusLong + " (" + motPlusLong.length() + " caractères)";
        this.statMotPlusCourt = motPlusCourt + " (" + motPlusCourt.length() + " caractères)";

        String motPlusFrequent = "";
        int comptePlus = 0;
        int compteMini = 0;
        int compteBoucle;
        for (int i = 0; i < mots.length; i++) {
            String motEnCours = mots[i];
            compteBoucle = (int) Arrays.stream(mots).filter(mot -> motEnCours.equals(mot)).count();
            if (compteBoucle > comptePlus) {
                comptePlus = compteBoucle;
                motPlusFrequent = mots[i] + " (" + String.valueOf(comptePlus) + " occurences)";
            }
            if (compteBoucle < compteMini) {
                compteMini = compteBoucle;
            }
        }
        this.statMotPlusFrequent = motPlusFrequent;
        this.statNbMiniOccurences = String.valueOf(compteMini);

        Statistiques.valTailleChaine = this.statNbCaracteres;
        Statistiques.valNbMots = this.statNbMots;
        Statistiques.valMotPlusGrand = this.statMotPlusGrand;
        Statistiques.valMotPlusCourt = this.statMotPlusCourt;
        Statistiques.valMotPlusFrequent = this.statMotPlusFrequent;
        Statistiques.valNbMiniOccurences = this.statNbMiniOccurences;
    }
}