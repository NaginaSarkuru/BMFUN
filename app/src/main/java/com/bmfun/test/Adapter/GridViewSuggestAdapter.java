package com.bmfun.test.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.bmfun.test.Common.Common;
import com.bmfun.test.GAME2;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private GAME2 game2;

    public GridViewSuggestAdapter(List<String> suggestSource, Context context, GAME2 game2) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.game2 = game2;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null)
        {
            if(suggestSource.get(position).equals("null"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //If correct answer contains character user select
                        if(String.valueOf(game2.answer).contains(suggestSource.get(position)))
                        {
                            char compare = suggestSource.get(position).charAt(0); //Get Char

                            for (int i=0;i<game2.answer.length;i++)
                            {
                                if (compare == game2.answer[i])
                                    Common.user_submit_answer[i] = compare;
                            }

                            //Update UI
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,context);
                            game2.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //Remove from suggest source
                            game2.suggestSource.set(position,"null");
                            game2.suggestAdapter = new GridViewSuggestAdapter(game2.suggestSource,context,game2);
                            game2.gridViewSuggest.setAdapter(game2.suggestAdapter);
                            game2.suggestAdapter.notifyDataSetChanged();

                        }
                        else //else
                        {
                            //Remove from suggest source
                            game2.suggestSource.set(position,"null");
                            game2.suggestAdapter = new GridViewSuggestAdapter(game2.suggestSource,context,game2);
                            game2.gridViewSuggest.setAdapter(game2.suggestAdapter);
                            game2.suggestAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        }
        else
            button = (Button)convertView;
        return button;
    }
    
    
}
