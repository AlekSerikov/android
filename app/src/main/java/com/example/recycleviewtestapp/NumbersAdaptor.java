package com.example.recycleviewtestapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdaptor extends RecyclerView.Adapter<NumbersAdaptor.NumberViewHolder> {
    private static int VIEW_HOLDER_COUNT;
    private int numberOfItems;
    private Context parent;

    public NumbersAdaptor(int numberOfItems, Context parent) {
        this.numberOfItems = numberOfItems;
        this.parent = parent;
        VIEW_HOLDER_COUNT = 0;
    }

    @NonNull
    @Override //create ViewHolder              //parent - RecycleView
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_element; // получили id xml файла опис-го наш элемент, по имени xml файла

        LayoutInflater inflater =  LayoutInflater.from(context); //позволяет из xml файла создать новый объект view
        View view = inflater.inflate(layoutIdForListItem, parent, false); //созданные view будут помещаться внутрь ViewGroup parent, т.е. RecycleView
        // false - recycleView сам поместит в себя
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText("View holder index: " + VIEW_HOLDER_COUNT);//устанавливаем значение index нашего view holder
        VIEW_HOLDER_COUNT++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        //уже созданные viewHolders будут использоваться заново, они будут заполнены новым содержимым
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberOfItems; //задаем количество элементов в нашем RecycleView
    }

    //class
    // класс описывающий элемент нашего RecycleView
    class NumberViewHolder extends RecyclerView.ViewHolder { //обертка для нашего числа, эл списка
        TextView listItemNumberView;                         //соттветсвуют двум TV в нашем представлении
        TextView viewHolderIndex;

        public NumberViewHolder(View itemView) {//наш объект сгенерированный из xml файла
            super(itemView);                    // ViewHolder обернет переданный объект
            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);

            itemView.setOnClickListener(v -> {
                int positionIndex =  getAdapterPosition();
                Toast toast = Toast.makeText(parent, "Element " + positionIndex + " was clicked!", Toast.LENGTH_SHORT);
                toast.show();
            });
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }

}
