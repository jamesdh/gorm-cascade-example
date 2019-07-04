package com.github

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    void "test saving"() {
        when: "saving w/ an assigned ID"
        def user = new User(id: 12345678, login: "mickymouse")
        user.save(flush: true)

        then: "verify item is persisted"
        assert User.count() == 1
        assert User.exists(user.id)
    }
}
