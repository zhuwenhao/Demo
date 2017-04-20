package com.zhuwenhao.demo.listener;

public interface OnMoveAndSwipedListener {

    void onItemMove(int fromPosition, int toPosition);

    void onItemSwiped(int position);
}
