package net.iesochoa.rutinapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iesochoa.rutinapp.R;
import net.iesochoa.rutinapp.models.Workouts;

import java.util.ArrayList;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.ViewHolder> {

    //VARIABLE IDENTIFICADORA
    private int resource;

    //ARRAYLIST CON TODOS LOS OBJETOS EJERCICIO QUE OBTENEMOS DE LA BD
    private ArrayList<Workouts> workoutsList;

    public WorkoutsAdapter(ArrayList<Workouts> workoutsList, int resource){
        /**
         * CONSTRUCTOR QUE RECIBE UNA LISTA DE EJERCICIOS
         */
        this.workoutsList = workoutsList;
        this.resource = resource;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        Workouts workouts = workoutsList.get(position);

        holder.tvNameWorkouts.setText(workouts.getName());
        holder.tvGroupWorkouts.setText(workouts.getGroup());
        //FALTAN LAS IMG
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
         * CLASE INTERNA CON LAS REFERENCIAS DE NUESTRAS VISTAS
         */
        private TextView tvNameWorkouts;
        private TextView tvGroupWorkouts;
        public View view;

        public ViewHolder(View view) {
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
