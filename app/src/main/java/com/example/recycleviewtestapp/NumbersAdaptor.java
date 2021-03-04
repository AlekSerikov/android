package com.example.recycleviewtestapp;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdaptor extends RecyclerView.Adapter<NumbersAdaptor.NumberViewHolder>{

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // здесь мы создаем один элемент нашего RecycleView
    class NumberViewHolder extends RecyclerView.ViewHolder { //обертка для элемента списка
        TextView listItemNumberView;                         //соттветсвуют двум TV в нашем представлении
        TextView viewHolderIndex;

        public NumberViewHolder(View itemView) {//наш объект сгенерированный из xml файла
            super(itemView);                             // ViewHolder обернет переданный объект

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);

        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }


}
