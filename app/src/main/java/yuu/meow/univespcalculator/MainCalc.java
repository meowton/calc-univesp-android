package yuu.meow.univespcalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class MainCalc extends AppCompatActivity {

  // Variáveis
  private int atividadesFeitas = 1;
  private double[] listaDeNotas = new double[8];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_calc);

    radioButtonListener();
  }

  // Listener que monitora o valor do RadioGroup de atividades realizadas.
  private void radioButtonListener() {
    RadioGroup radioAtvFeitas = findViewById(R.id.radioGroupAtvFeitas);
    radioAtvFeitas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton;
        switch (checkedId) {
          case R.id.radioAtvFeitas1:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          case R.id.radioAtvFeitas2:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          case R.id.radioAtvFeitas3:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          case R.id.radioAtvFeitas4:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          case R.id.radioAtvFeitas5:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          case R.id.radioAtvFeitas6:
            radioButton = findViewById(checkedId);
            atividadesFeitas = Integer.parseInt(radioButton.getText().toString());
            break;
          default:
            atividadesFeitas = 1;
        }
      }
    });
  }

  // Botão de cálculo de médias, principal disparador de eventos.
  public void btnCalcularMediaPressed(View view) {
    storeGrades();
    gradeCalculation();
    hideKeyboard();

    // Test dialog
//    showDialog("Information:", "Atividades feitas: "
//            + atividadesFeitas + ". Notas: " + listaDeNotas[0]
//            + ", " + listaDeNotas[1] + ", " + listaDeNotas[2]
//            + ", " + listaDeNotas[3] + ", " + listaDeNotas[4]
//            + ", " + listaDeNotas[5] + ", " + listaDeNotas[6]
//            + ", " + listaDeNotas[7]);

  }

  // Limpa todos os valores inseridos nos campos.
  public void btnLimparPressed(View view) {

    showKeyboard();

    RadioButton radioButton = findViewById(R.id.radioAtvFeitas1);
    radioButton.setChecked(true);

    EditText inputAtv1 = findViewById(R.id.inputAtvNota1);
    inputAtv1.setText(null);
    // inputAtv1.requestFocus();
    EditText inputAtv2 = findViewById(R.id.inputAtvNota2);
    inputAtv2.setText(null);
    EditText inputAtv3 = findViewById(R.id.inputAtvNota3);
    inputAtv3.setText(null);
    EditText inputAtv4 = findViewById(R.id.inputAtvNota4);
    inputAtv4.setText(null);
    EditText inputAtv5 = findViewById(R.id.inputAtvNota5);
    inputAtv5.setText(null);
    EditText inputAtv6 = findViewById(R.id.inputAtvNota6);
    inputAtv6.setText(null);
    EditText inputProva = findViewById(R.id.inputAtvNotaProva);
    inputProva.setText(null);
    EditText inputExame = findViewById(R.id.inputAtvNotaExame);
    inputExame.setText(null);

    TextView notaAtividades = findViewById(R.id.notaTotalDeAtividades);
    notaAtividades.setText("0.0");
    TextView notaProva = findViewById(R.id.notaDaProva);
    notaProva.setText("0.0");
    TextView notaExame = findViewById(R.id.notaDoExame);
    notaExame.setText("0.0");
    TextView mediaAtividades = findViewById(R.id.mediaDasAtividades);
    mediaAtividades.setText("0.0");
    TextView mediaProva = findViewById(R.id.mediaDaProva);
    mediaProva.setText("0.0");
    // TextView mediaExame = findViewById(R.id.mediaDoExame);
    // mediaExame.setText("0.0");
    TextView mediaBimestre = findViewById(R.id.mediaDoBimestre);
    mediaBimestre.setText("0.0");
    TextView aprRprStatus = findViewById(R.id.status);
    aprRprStatus.setText(null);

  }

  // Coleta as notas e adiciona ao array. Possui checagem de tipo.
  private void storeGrades() {

    try {
      EditText inputAtv1 = findViewById(R.id.inputAtvNota1);
      checkNull(inputAtv1);
      listaDeNotas[0] = Double.parseDouble(inputAtv1.getText().toString());

      EditText inputAtv2 = findViewById(R.id.inputAtvNota2);
      checkNull(inputAtv2);
      listaDeNotas[1] = Double.parseDouble(inputAtv2.getText().toString());

      EditText inputAtv3 = findViewById(R.id.inputAtvNota3);
      checkNull(inputAtv3);
      listaDeNotas[2] = Double.parseDouble(inputAtv3.getText().toString());

      EditText inputAtv4 = findViewById(R.id.inputAtvNota4);
      checkNull(inputAtv4);
      listaDeNotas[3] = Double.parseDouble(inputAtv4.getText().toString());

      EditText inputAtv5 = findViewById(R.id.inputAtvNota5);
      checkNull(inputAtv5);
      listaDeNotas[4] = Double.parseDouble(inputAtv5.getText().toString());

      EditText inputAtv6 = findViewById(R.id.inputAtvNota6);
      checkNull(inputAtv6);
      listaDeNotas[5] = Double.parseDouble(inputAtv6.getText().toString());

      EditText inputProva = findViewById(R.id.inputAtvNotaProva);
      checkNull(inputProva);
      listaDeNotas[6] = Double.parseDouble(inputProva.getText().toString());

      EditText inputExame = findViewById(R.id.inputAtvNotaExame);
      checkNull(inputExame);
      listaDeNotas[7] = Double.parseDouble(inputExame.getText().toString());

    } catch (InputMismatchException ex) {
      showDialog("Exceção: caractere inválido", "Um caractere não suportado foi entrado. " +
              "Insira apenas números inteiros ou números reais separados por ponto. " +
              "(Error: InputMismatchException)");
    } catch (IllegalArgumentException ex) {
      showDialog("Exceção: caractere nulo", "Um caractere inválido foi entrado. " +
              "Insira apenas números inteiros ou números reais separados por ponto. " +
              "(Error: IllegalArgumentException)");
    }
  }

  // Checa se o valor inserido pelo usuário é nulo. Caso for, será transformado em 0.
  private void checkNull(EditText editText) {
    if (editText.getText().toString().trim().length() == 0) {
      editText.setText("0");
    }
  }

  private void gradeCalculation() {
    // Notas constantes da UNIVESP
    final double univespAtvMedia = 0.4;
    final double univespMediaProva = 0.6;

    // Soma das notas de todas as atividades feitas.
    double somaDasNotasDasAtividades = 0;
    for (int i = 0; i < listaDeNotas.length - 2; i++) {
      somaDasNotasDasAtividades += listaDeNotas[i];
    }
    // Cálculo da nota das atividades. A UNIVESP divide a soma das notas
    // pela quantidade de atividades disponibilizadas no bimestre.
    double notaDasAtividades;
    notaDasAtividades = (somaDasNotasDasAtividades / atividadesFeitas);

    // Cálculo da média das atividades. É pego a nota das atividades e
    // multiplicado por uma porção (atual: 0.4), o valor inicial foi de 0.49.
    double mAtividades;
    mAtividades = (notaDasAtividades * univespAtvMedia);
    // Cálculo da média da prova. É pego o valor total da prova e
    // multiplicado por uma porção, assim como nas atividades, sendo
    // no entanto de 0.6 (inicialmente era de 0.51).
    double mProva;
    mProva = (listaDeNotas[6] * univespMediaProva);
    // double mExame;
    // mExame = (listaDeNotas[7] * 0.6);

    // A média do bimestre é obtida pela soma da média das atividades
    // com a média da prova e/ou do exame.
    double mBimestre;
    mBimestre = (mAtividades + mProva);

    // A média com o exame é obtida somando a média final anterior com
    // a nota obtida no exame, e então dividindo por 2.
    double mBimComExam;
    mBimComExam = ((mBimestre + listaDeNotas[7]) / 2);

    // Setter de textos.
    txtSetters(notaDasAtividades, mAtividades, mProva, mBimestre, mBimComExam);

  }

  private void txtSetters(double notaAtv, double mAtv, double mPr,
                          double mBim, double mBimComEx) {

    // Controla a precisão dos cálculos que serão exibitos
    final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    TextView notaAtividades = findViewById(R.id.notaTotalDeAtividades);
    notaAtividades.setText(decimalFormat.format(notaAtv));
    TextView notaProva = findViewById(R.id.notaDaProva);
    notaProva.setText(decimalFormat.format(listaDeNotas[6]));

    TextView mediaAtividades = findViewById(R.id.mediaDasAtividades);
    mediaAtividades.setText(decimalFormat.format(mAtv));
    TextView mediaProva = findViewById(R.id.mediaDaProva);
    mediaProva.setText(decimalFormat.format(mPr));

    TextView notaExame = findViewById(R.id.notaDoExame);
    // TextView mediaExame = findViewById(R.id.mediaDoExame);
    // mediaExame.setText(decimalFormat.format(mEx));
    TextView mediaBimestre = findViewById(R.id.mediaDoBimestre);

    // Média final e exame
    if (listaDeNotas[7] > 0) {
      notaExame.setText(decimalFormat.format(listaDeNotas[7]));
      mediaBimestre.setText(decimalFormat.format(mBimComEx));
      status(mBimComEx);

    } else {
      mediaBimestre.setText(decimalFormat.format(mBim));
      notaExame.setText(decimalFormat.format(0.0));
      status(mBim);
    }
  }

  private void status(double media) {
    TextView aprRprStatus = findViewById(R.id.status);
    int compare = Double.compare(media, 5.0);
    if (compare >= 0) {
      aprRprStatus.setTextColor(Color.parseColor("Green"));
      aprRprStatus.setText(R.string.aprovado);
    } else {
      aprRprStatus.setTextColor(Color.parseColor("Red"));
      aprRprStatus.setText(R.string.reprovado);
    }
  }

  private void showDialog(String title, String message) {
    AlertDialog alertDialog = new AlertDialog.Builder(MainCalc.this).create();
    alertDialog.setTitle(title);
    alertDialog.setMessage(message);
    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            });
    alertDialog.show();
  }

  private void hideKeyboard() {
    try {
      InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    } catch (NullPointerException ex) {
      showDialog("Exceção: NullPointer", "Não há foco na View atual.");
    }
  }

  private void showKeyboard() {
    try {
      TextView atv1 = findViewById(R.id.inputAtvNota1);
      atv1.requestFocus();
      InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.showSoftInput(atv1, InputMethodManager.SHOW_IMPLICIT);
    } catch (NullPointerException ex) {
      showDialog("Exceção: NullPointer", "Não há foco na View atual.");
    }
  }
}
