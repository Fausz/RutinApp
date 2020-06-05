package net.iesochoa.rutinapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iesochoa.rutinapp.R;
import net.iesochoa.rutinapp.activities.WorkoutsActivity;
import net.iesochoa.rutinapp.activities.WorkoutsDetailsActivity;
import net.iesochoa.rutinapp.models.Workouts;

import java.util.ArrayList;

import static net.iesochoa.rutinapp.activities.WorkoutsDetailsActivity.EXTRA_MOSTRAR_DATOS;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.ViewHolder> {

    //OBJETO CONTEXTO
    private Context mContext;

    //VARIABLE IDENTIFICADORA
    private int resource;

    //ARRAYLIST CON TODOS LOS OBJETOS EJERCICIO QUE OBTENEMOS DE LA BD
    private ArrayList<Workouts> workoutsList;

    public WorkoutsAdapter(Context applicationContext,ArrayList<Workouts> workoutsList, int resource){
        /**
         * CONSTRUCTOR QUE RECIBE UNA LISTA DE EJERCICIOS
         */

        mContext = applicationContext;
        this.workoutsList = workoutsList;
        this.resource = resource;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        /**
         * MÉTODO DONDE SE CREA LA VISTA
         */

        //OBTENEMOS LA VISTA XML Y LA INFLAMOS PASANDOLE UNA VARIABLE ENTERA CON EL IDENTIFICADOR
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);

        //RETORNAMOS LA VISTA
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        /**
         * MÉTODO DONDE DEFINIMOS/ESTABLECEMOS LOS DATOS QUE QUEREMOS QUE SE MUESTREN EN LAS VISTAS
         */

        //ESTABLECEMOS LOS VALORES QUE OBTENEMOS DE LA BD
        final Workouts workouts = workoutsList.get(position);


        holder.tvNameWorkouts.setText(workouts.getName());
        holder.tvGroupWorkouts.setText(workouts.getGroup());
        //FALTAN LAS IMG

        //EVENTO PARA SELECCIONAR UN ITEM DE LA LISTA
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT CON EL CONTEXTO DE LA ACTIVITY
                Intent intent = new Intent(mContext,WorkoutsDetailsActivity.class);
                //SETEO EL OBJETO DEL ITEM SELECCIONADO AL INTENT PARA ENVIAR A LA ACTIVITY WORKOUTSDETAILSACTIVITY MEDIANTE LA VARIABLE EXTRA_MOSTRAR_DATOS(EN LA ACTIVITY DESTINO)
                intent.putExtra(EXTRA_MOSTRAR_DATOS,workouts);

                mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


    }

    @Override
    public int getItemCount() {
        /**
         * MÉTODO QUE NOS RETORNA EL NÚMERO DE LISTAS QUE ESTAMOS OBTENIENDO
         */
        return workoutsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * CLASE INTERNA CON LAS REFERENCIAS DE NUESTRAS VISTAS A LOS ITEMS
         */
        private TextView tvNameWorkouts;
        private TextView tvGroupWorkouts;
        public View view;

        public ViewHolder(final View view) {
            /**
             * CONSTRUCTOR QUE RECIBE COMO PARÁMETRO UNA VISTA
             */
            //CON EL METODO SUPER LE PASAMOS LA VISTA
            super(view);
            this.view=view;

            //CASTEAMOS LOS VIEWS CON LA REFERENCIA DE LAS VISTAS
            this.tvNameWorkouts = (TextView) view.findViewById(R.id.tvNameWorkouts);
            this.tvGroupWorkouts = (TextView) view.findViewById(R.id.tvGroupWorkouts);

        }
    }
}
