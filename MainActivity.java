package version1.teegolf;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Aquestes variables permitiran obtenir els controladors creats i així poder manipular-los

    EditText edtRFID;
    EditText edtClubName;
    EditText edtTeeHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapegem les variables creades en els controls. D'aquesta manera podem modificar els valors.
        edtRFID = (EditText) findViewById(R.id.edtRFID);
        edtClubName = (EditText) findViewById(R.id.edtClubName);
        edtTeeHeight = (EditText) findViewById(R.id.edtTeeHeight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Assignem el menu a l'activitat
        getMenuInflater().inflate(R.menu.menu_tee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        //Segons a l'icona selecccionat, es fa una acció o altre

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_add: // Treballem amb tots els controladors que defineixen el client

                String rfid = edtRFID.getText().toString();
                String clubName = edtClubName.getText().toString();
                String teeHeight = edtTeeHeight.getText().toString();

                //Validem que ingressin els tred camps

                if(rfid.length() > 0 && clubName.length() > 0 && teeHeight.length() >0){

                    // Obrim la base de dades dels clients

                    TeegolfSQLiteHelper teeGolf = new TeegolfSQLiteHelper(this, "DBTeeGolf", null, 1);
                    SQLiteDatabase db = teeGolf.getWritableDatabase();

                    db.execSQL("INSERT INTO TeeGolf (RFID, ClubName, TeeHeight) VALUES (" + rfid + ",'" + clubName + "','" + teeHeight + "')");
                    db.close();
                    Toast.makeText(this, "El teeGolf se ha creado exitosamente", Toast.LENGTH_SHORT).show();
                    edtRFID.setText("");
                    edtClubName.setText("");
                    edtTeeHeight.setText("");
                }else{
                    Toast.makeText(this, "Debe ingresar todos los datos asociados al usuario", Toast.LENGTH_SHORT).show();

                }



                return  true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }
}
