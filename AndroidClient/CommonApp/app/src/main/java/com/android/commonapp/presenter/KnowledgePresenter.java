package com.android.commonapp.presenter;

import com.android.commonapp.contact.KnowledgeCourseContact;
import com.android.commonapp.contact.TestContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.KnowledgeModel;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe: 知识课件
 */

public class KnowledgePresenter extends BasePresenterImpl<KnowledgeCourseContact.view> implements KnowledgeCourseContact.presenter {

    public KnowledgePresenter(KnowledgeCourseContact.view view) {
        super(view);
    }

    @Override
    public void getKnowledgeCourse(String id,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("数据传输中，请稍后",false,false);
        RetrofitFactory.getInstance().api().knowledgecourse(id,page, size)
            .enqueue(new CommonCallback<List<KnowledgeModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<KnowledgeModel>> t) throws Exception {
                    view.dismissLoadingDialog();
                    if (t.getData() != null) {
                        view.success(t.getData());
                    }
                }

                @Override
                protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                    view.dismissLoadingDialog();
                    view.failure(e, isNetWorkError, msg);
                }
            });
    }
}
