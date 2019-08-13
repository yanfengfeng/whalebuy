//package com.android.whalebuy;
//
//
//import com.android.whalebuy.been.BasePresenter;
//import com.android.whalebuy.been.HomeBottom;
//import com.android.whalebuy.been.HomeTop;
//
///**
// *
// *
// */
//
//public interface HomeContract {
//    interface View extends BaseView {
//        void show(HomeTop bean);
//
//        void show(HomeBottom bean);
//
//        void loading();
//
//        void networkError();
//
//        void error(String msg);
//
//        void showFailed(String msg);
//    }
//
//    interface Presenter extends BasePresenter {
//        void start(int type);
//
//        void start(int type, int page, int pageSize);
//    }
//}
