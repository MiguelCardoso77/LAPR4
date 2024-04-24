/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package core.domain.customer;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class CustomerBuilder implements DomainFactory<Customer> {

    private SystemUser systemUser;
    private TelephoneNumber telephoneNumber;
    private Company company;

    // private String CustomerManager;

    public CustomerBuilder withAll(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Company company){
        Preconditions.nonNull(systemUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(company);
        this.systemUser = systemUser;
        this.telephoneNumber = telephoneNumber;
        this.company = company;
        return this;
    }
    public CustomerBuilder withTelephoneNumber(TelephoneNumber telephoneNumber) {
        Preconditions.nonNull(telephoneNumber);
        this.telephoneNumber = telephoneNumber;
        return this;
    }
    /**
     * Builds a client user.
     *
     * @return the built client user
     */
    @Override
    public Customer build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Customer(this.systemUser, this.telephoneNumber, this.company);
    }

}
