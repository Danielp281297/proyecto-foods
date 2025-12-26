package com.daniel.padilla.compactpos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    // REALM DATABASE
    @Singleton
    @Provides
    fun provideRealmDatabase(
        //@Named("ENCRIPTATION_KEY") key: ByteArray,
    ): Realm {

        val configuration = RealmConfiguration
            .Builder(schema = setOf(TransactionObject::class, ProductoObject::class))
            .name("disconnect.realm")
            ///.encryptionKey(encryptionKey)
            .build()

        return Realm.open(configuration)

    }*/


    /*
    //BASE DE DATOS
    //CONTEXTO
    //DEVICE ENGINE
    @Provides
    @Singleton
    fun provideDeviceEngine(
        @ApplicationContext context: Context
    ): DeviceEngine {

        var deviceEngine: DeviceEngine? = null

        try {
            deviceEngine = APIProxy.getDeviceEngine(context)
            deviceEngine?.getEmvHandler("app1")
        } catch (err: Exception) {
            println("xxoxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Exception $err")
        } catch (err: Throwable) {
            println("xxoxxxxxxxxxxxxxxxxxxxxxxxxxxxxx android Throwable")
        }

        return deviceEngine!!

    }

    //RETROFIT
    @Singleton
    @Provides
    @Named("TERMINAL_URL")
    fun providerTerminalUrl() = BASE_URL + "terminal/v0/"

    @Singleton
    @Provides
    @Named("TRANSACTION_URL")
    fun providerTransactionlUrl() = BASE_URL + "transaction/v0/"

    @Provides
    @Singleton
    fun provideTerminalRetrofit(
        @ApplicationContext context: Context,
        @Named("TERMINAL_URL") URL: String
    ): TerminalDataSource
    {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.client(createCustomOkHttpClient(context, false))
            .baseUrl(URL)
            .build()
            .create(TerminalDataSource::class.java)

    }

    @Provides
    @Singleton
    fun provideTransactionRetrofit(
        @ApplicationContext context: Context,
        @Named("TRANSACTION_URL") URL: String
    ): TransactionDataSource
    {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.client(createCustomOkHttpClient(context, false))
            .baseUrl(URL)
            .build()
            .create(TransactionDataSource::class.java)

    }


    @Singleton
    @Provides
    @Named("ENCRIPTATION_KEY")
    fun provideEncryptionKey(
        @ApplicationContext context: Context,
    ): ByteArray {

        val key = ByteArray(64)
        try {
            SecureRandom().nextBytes(key)
            //
            // LÓGICA DE ALMACENAMIENTO SEGURO EN ANDROID KEYSTORE DEBE IR AQUÍ
            CommerceLocalData.getTokenAuth(context)

        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
        }
        return key
    }*/

}