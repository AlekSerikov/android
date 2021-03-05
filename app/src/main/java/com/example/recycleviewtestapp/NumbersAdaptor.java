package com.example.recycleviewtestapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdaptor extends RecyclerView.Adapter<NumbersAdaptor.NumberViewHolder> {
    private static int viewHolderCount;
    private int numberOfItems;

    public NumbersAdaptor(int numberOfItems) {
        this.numberOfItems = numberOfItems;
        viewHolderCount = 0;
    }

    @NonNull
    @Override //create ViewHolder              //patent - RecycleView
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_element; // получили id xml файла опис-го наш элемент

        LayoutInflater inflater =  LayoutInflater.from(context); //позволяет из xml файла создать новый объект view
        View view = inflater.inflate(layoutIdForListItem, parent, false); //созданные view будут помещаться внутрь ViewGroup parent, т.е. RecycleView
                                                                                       // false - recycleView сам поместит в себя
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText("View holder index: " + viewHolderCount);//устанавливаем значение index нашего view holder
        viewHolderCount++;

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
    // здесь мы создаем один элемент нашего RecycleView
    class NumberViewHolder extends RecyclerView.ViewHolder { //обертка для элемента списка
        TextView listItemNumberView;                         //соттветсвуют двум TV в нашем представлении
        TextView viewHolderIndex;

        public NumberViewHolder(View itemView) {//наш объект сгенерированный из xml файла
            super(itemView);                    // ViewHolder обернет переданный объект

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }

}
