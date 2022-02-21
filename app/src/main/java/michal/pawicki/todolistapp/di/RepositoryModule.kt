package michal.pawicki.todolistapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import michal.pawicki.todolistapp.data.AplicationRepositoryImpl
import michal.pawicki.todolistapp.domain.AplicationRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAplicationRepository(instance: AplicationRepositoryImpl): AplicationRepository
}