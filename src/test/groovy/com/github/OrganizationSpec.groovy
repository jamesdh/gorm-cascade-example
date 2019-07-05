package com.github

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class OrganizationSpec extends Specification implements DomainUnitTest<Organization> {

    void "test saving"() {
        when: "saving"
        def org = new Organization(login: "mice")
        org.save(flush: true)

        then: "verify item is persisted"
        assert Organization.count() == 1
        assert Organization.exists(org.id)
    }
}
