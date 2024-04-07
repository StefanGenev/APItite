package com.example.APItite.Repo

import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.transaction.annotation.Transactional


@NoRepositoryBean
class RefreshableCRUDRepositoryImpl<T, ID>(

    entityInformation: JpaEntityInformation<T, ID>?,
    private val entityManager: EntityManager

) : SimpleJpaRepository<T, ID>(entityInformation!!, entityManager), RefreshableCrudRepository<T, ID> {
    @Transactional
    override fun flush() {
        entityManager.flush()
    }

    @Transactional
    override fun refresh(t: T) {
        entityManager.refresh(t)
    }

    @Transactional
    override fun refresh(s: Collection<T>?) {
        for (t in s!!) {
            entityManager.refresh(t)
        }
    }
}