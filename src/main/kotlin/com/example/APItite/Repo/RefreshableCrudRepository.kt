package com.example.APItite.Repo

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean


@NoRepositoryBean
interface RefreshableCrudRepository<T, ID> : CrudRepository<T, ID> {
    fun refresh(t: T)

    fun refresh(s: Collection<T>?)

    fun flush()
}