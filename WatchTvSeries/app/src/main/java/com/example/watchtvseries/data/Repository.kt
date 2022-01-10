package com.example.watchtvseries.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteData: RemoteData){
    val remoteDatas = remoteData
}