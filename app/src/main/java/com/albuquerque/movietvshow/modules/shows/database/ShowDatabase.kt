package com.albuquerque.movietvshow.modules.shows.database

import com.albuquerque.movietvshow.core.database.BaseDatabase
import com.albuquerque.movietvshow.modules.shows.model.Show
import io.realm.Realm

object ShowDatabase : BaseDatabase() {

    fun removeFavorite(showId: Int) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.where(Show::class.java).equalTo(Show::id.name, showId).findFirst()?.deleteFromRealm()
            realm.commitTransaction()
        }
    }

    fun getShowFromDB(id: Int): Show? {
        val realm = Realm.getDefaultInstance()

        realm.where(Show::class.java).equalTo(Show::id.name, id).findFirst()?.let {
            return realm.copyFromRealm(it)
        } ?: kotlin.run {
            return null
        }

    }

    fun getFavorites(): List<Show> {
        return Realm.getDefaultInstance().use { realm ->
            realm.where(Show::class.java)
                    .equalTo(Show::isFavorite.name, true)
                    .limit(20)
                    .findAll()

        }
    }

}