package com.bts.flickr.ui.search

import com.bts.flickr.R
import com.bts.flickr.config.ApiConfig
import com.bts.flickr.data.network.RetrofitManager
import com.bts.flickr.data.resource.ResourceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(private val resourceManager: ResourceManager, private val retrofitManager: RetrofitManager) : SearchContract.Presenter {
    private val PERPAGE_LIMIT_VALUE = 20

    private val DATA_FORMAT_VALUE = "json"
    private var view: SearchContract.View? = null

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun bind(view: SearchContract.View) {
        this.view = view
    }

    override fun unbind() {
        disposable.dispose()
        this.view = null
    }

    override fun loadData(method: String, apiKey: String) {
        view?.showLoadingIndicator()
        disposable.add(retrofitManager
                .getList(method, apiKey, PERPAGE_LIMIT_VALUE, DATA_FORMAT_VALUE, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.hideLoadingIndicator() }
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view?.onSuccess(it)
                        }
                    } else view?.onError(response.message())

                }, { t ->
                    view?.onError(t.message!!)
                }))
    }

    override fun searchImageByTag(apiKey: String, tag: String) {
        view?.showLoadingIndicator()
        disposable.add(retrofitManager
                .getImagesByTag(ApiConfig.METHOD_SEARCH, apiKey, tag, PERPAGE_LIMIT_VALUE, DATA_FORMAT_VALUE, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.hideLoadingIndicator() }
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view?.onSuccess(it)
                        }
                    } else view?.onError(resourceManager.getStringResource(R.string.msg_connection_failure))

                }, { t ->
                    view?.onError(t.message!!)
                }))
    }
}
