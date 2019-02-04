package com.obiomaofoamalu.albums.albumlist

import android.util.Log
import com.obiomaofoamalu.albums.albumlist.domain.IAlbumListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The [AlbumListPresenter] is the presenter class for [AlbumListActivity] following the MVP pattern.
 *
 * @constructor Instantiates the [AlbumListPresenter] object.
 * @param interactor [IAlbumListInteractor]
 */
class AlbumListPresenter @Inject constructor(
    private val interactor: IAlbumListInteractor
) {

    private var disposable: Disposable? = null
    private var view: IAlbumListView? = null
    private val tag: String = AlbumListPresenter::class.java.simpleName

    /**
     * Loads the data to be displayed
     */
    fun loadAlbums() {
        if (disposable == null) {
            disposable = interactor.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it -> displayInView(it) },
                    { throwable -> Log.e(tag, throwable.message) }
                )
        }
    }

    /**
     * Initializes the [IAlbumListView]
     */
    fun init(view: IAlbumListView) {
        this.view = view
    }

    /**
     * Disposes the [disposable]
     */
    fun dispose() {
        disposable?.let {
            dispose()
            disposable = null
        }
        view?.let {
            view = null
        }
    }

    private fun displayInView(albums: List<AlbumViewModel>) {
        if (view == null) {
            Log.e(tag, "Error: presenter.init() was not called. IAlbumListView is null.")
        } else {
            view!!.display(albums)
        }
    }

    /**
     * The [IAlbumListView] is the View component of the MVP pattern.
     *
     * @see AlbumListActivity
     */
    interface IAlbumListView {

        /**
         * Displays the data
         *
         * @param albums is the data to display
         */
        fun display(albums: List<AlbumViewModel>)
    }
}
