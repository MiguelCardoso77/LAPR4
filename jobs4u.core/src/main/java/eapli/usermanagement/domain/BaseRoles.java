/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class BaseRoles {
    public static final Role POWER_USER = Role.valueOf("POWER_USER");
    public static final Role CLIENT_USER = Role.valueOf("CLIENT_USER");
    public static final Role KITCHEN_MANAGER = Role.valueOf("KITCHEN_MANAGER");
    public static final Role MENU_MANAGER = Role.valueOf("MENU_MANAGER");
    public static final Role CASHIER = Role.valueOf("CASHIER");

    public static final Role ADMIN = Role.valueOf("ADMIN");
    public static final Role CANDIDATE = Role.valueOf("CANDIDATE");
    public static final Role CUSTOMER = Role.valueOf("CUSTOMER");
    public static final Role CUSTOMER_MANAGER = Role.valueOf("CUSTOMER_MANAGER");
    public static final Role LANGUAGE_ENGINEER = Role.valueOf("LANGUAGE_ENGINEER");
    public static final Role OPERATOR = Role.valueOf("OPERATOR");

    /**
     * get available role types for adding new users
     *
     * @return
     */
    public static Role[] nonUserValues() {
        return new Role[] { ADMIN, KITCHEN_MANAGER, MENU_MANAGER, CASHIER };
    }

    public boolean isCollaborator(final Role role) {
        return role != CLIENT_USER;
    }
}
