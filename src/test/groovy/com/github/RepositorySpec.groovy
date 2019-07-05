package com.github

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RepositorySpec extends Specification implements DomainUnitTest<Repository> {

    void "test saving"() {
        when: "saving"
        def repo = new Repository(name: "cheese")
        repo.save(flush: true)

        then: "verify item is persisted"
        assert Repository.count() == 1
        assert Repository.exists(repo.id)
    }
}
