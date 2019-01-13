package com.albuquerque.tvshow.modules.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.database.AuthDatabase
import com.albuquerque.tvshow.modules.auth.model.User
import com.albuquerque.tvshow.modules.auth.utils.AuthUtils

class AuthViewModel : ViewModel() {

    val onLoginSucess = SingleLiveEvent<Void>()
    val onLogoutSucess = SingleLiveEvent<Void>()
    val onError = SingleLiveEvent<String>()
    val onUserLogged = SingleLiveEvent<Void>()

    private lateinit var usuario: MutableLiveData<User>

    init {
        if(AuthDatabase.getSessionId().isNotBlank())
            onUserLogged.call()
    }

    fun handlerLogin(user: String, pass: String){
        requestLogin(user, pass)
    }

    fun handlerLogout(){
        requestLogout()
    }

    fun getLoggedUser(): LiveData<User>{
        if (!::usuario.isInitialized) {
            usuario = MutableLiveData()
            usuario.value = AuthDatabase.getUser()
        }

        return usuario
    }

    private fun requestLogin(user: String, pass: String){

        AuthBusiness.doLogin(user, pass,

                onSuccess = { it ->
                    AuthDatabase.saveOrUpdate(it)
                    onLoginSucess.call()
                },

                onError = { error ->
                    onError.value = AuthUtils.geErrorMessage(error) ?: "Erro!!!"
                    onError.call()
                }
        )

    }

    private fun requestLogout(){
        AuthBusiness.doLogout(
                {
                    AuthDatabase.clearDatabase()
                    onLogoutSucess.call()
                },
                { error ->
                    onError.value = AuthUtils.geErrorMessage(error) ?: "Erro!!!"
                    onError.call()
                }
        )
    }

}