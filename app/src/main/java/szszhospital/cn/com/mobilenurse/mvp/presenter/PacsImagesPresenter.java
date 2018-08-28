package szszhospital.cn.com.mobilenurse.mvp.presenter;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;

import org.apache.commons.net.ftp.FTPFile;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.utils.FtpUtil;

public class PacsImagesPresenter extends RxPresenter<PacsImagesContract.View, PacsImagesContract.Model> implements PacsImagesContract.Presenter {
    private static final String TAG       = "PacsImagesPresenter";
    public static final  String FTP_PATH  = "172.18.0.143";
    public static final  String USER_NAME = "annetftp";
    public static final  String PASSWORD  = "annet";
    private FtpUtil mFtp;

    public PacsImagesPresenter() {
        mFtp = new FtpUtil();
        FtpConnect();
    }

    private void FtpConnect() {
        if (NetworkUtils.isConnected()) {
            App.getAsynHandler().post(() -> {
                try {
                    mFtp.connect(FTP_PATH, USER_NAME, PASSWORD);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void getPacsImages(String studyId, String type) {
        mView.showProgress();
        ApiService.Instance().getService()
                .getPacsImageFtpPath(studyId, type)
                .subscribeOn(Schedulers.io())
                .compose(RxUtil.httpHandleResponse())
                .flatMap(new Function<List<PacsImagePath>, ObservableSource<List<PacsImagePath>>>() {
                    @Override
                    public ObservableSource<List<PacsImagePath>> apply(List<PacsImagePath> pacsImagePaths) throws Exception {
                        if (!mFtp.isConnected()) {
                            mFtp.connect(FTP_PATH, USER_NAME, PASSWORD);
                        }
                        for (int i = 0; i < pacsImagePaths.size(); i++) {
                            PacsImagePath obj = pacsImagePaths.get(i);
                            String ftpPath = obj.IMAGEPATH;
                            FTPFile[] ftpFiles = mFtp.getFtpClient().listFiles(ftpPath);
                            for (int j = 0; j < ftpFiles.length; j++) {
                                String fileName = ftpFiles[j].getName();
                                Log.d(TAG, "apply: " + fileName);
                                DcmName dcmName = new DcmName();
                                dcmName.IMAGENAME = fileName;
                                dcmName.IMAGEPATH = ftpPath;
                                dcmName.save();
                                if (j == 0) {
                                    obj.thumbnailPath = ftpPath + fileName;
                                    obj.name = fileName;
                                }
                            }
                            obj.save();
                        }
                        return Observable.just(pacsImagePaths);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PacsImagePath>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<PacsImagePath> pacsImagePaths) {
                        if (pacsImagePaths != null && pacsImagePaths.size() > 0) {
                            mView.getRealImagePath(pacsImagePaths);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        mFtp.disconnect();
    }
}
