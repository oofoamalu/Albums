package com.obiomaofoamalu.albums

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.obiomaofoamalu.albums.data.IAlbumRepository
import com.obiomaofoamalu.albums.di.Injector
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumListActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: IAlbumRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        Injector.applicationComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()
        repository.getAlbums()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { println("Albums size is ${it.size}") },
                { throwable -> println("An error occurred: ${throwable.message}") }
            )
    }
}
