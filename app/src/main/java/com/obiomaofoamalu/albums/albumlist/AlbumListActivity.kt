package com.obiomaofoamalu.albums.albumlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.obiomaofoamalu.albums.R
import com.obiomaofoamalu.albums.di.Injector
import kotlinx.android.synthetic.main.activity_album_list.*
import javax.inject.Inject

/**
 * The [AlbumListActivity] implements the [AlbumListPresenter.IAlbumListView] interface
 * following the MVP pattern. It displays a list of albums.
 */
class AlbumListActivity : AppCompatActivity(), AlbumListPresenter.IAlbumListView {

    @Inject
    lateinit var presenter: AlbumListPresenter

    private val adapter: AlbumListAdapter = AlbumListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.applicationComponent.inject(this)
        setContentView(R.layout.activity_album_list)
        initView()
    }

    override fun onStart() {
        super.onStart()
        presenter.init(this)
        presenter.loadAlbums()
    }

    override fun onStop() {
        super.onStop()
        presenter.dispose()
    }

    override fun display(albums: List<AlbumViewModel>) {
        adapter.updateData(albums)
    }

    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
