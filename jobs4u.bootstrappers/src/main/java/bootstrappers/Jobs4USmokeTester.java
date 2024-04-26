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
package bootstrappers;

import bootstrappers.Jobs4UBootstrapper;
import bootstrappers.smoketests.MasterUsersSmokeTester;
import core.domain.user.Jobs4URoles;
import core.domain.user.UserBuilderHelper;
import core.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * execute simple smoke tests on the application layer. we are assuming that the
 * bootstrappers mainly test the "register" use cases and some of the "finders"
 * to support those "register", so this class will focus mainly on executing the
 * other application methods
 *
 */
@SuppressWarnings("squid:S1126")
public class Jobs4USmokeTester implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jobs4UBootstrapper.class);
    private static final String BOOTSTRAP = "bootstrap@gmail.com";
    private static final String BOOTSTRAP_PWD = "Bootstrap9000";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    @Override
    public boolean execute() {
        final Action[] actions = {new MasterUsersSmokeTester()};

        registerBootstrapTestAccount();
        authenticateForTestBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }

        return ret;
    }

    private void registerBootstrapTestAccount() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(BOOTSTRAP).withPassword(BOOTSTRAP_PWD).withName("BootstrapTest", "MachineTest").withEmail("bootstrapmachine@gmail.com").withRoles(Jobs4URoles.BOOTSTRAP);
        final SystemUser newTestUser = userBuilder.build();

        SystemUser testBootstrapper;
        try {
            testBootstrapper = userRepository.save(newTestUser);
            assert testBootstrapper != null;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newTestUser.username());
            LOGGER.trace("Assuming existing record", e);
        }
    }

    protected void authenticateForTestBootstrapping() {
        authenticationService.authenticate(BOOTSTRAP, BOOTSTRAP_PWD);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
