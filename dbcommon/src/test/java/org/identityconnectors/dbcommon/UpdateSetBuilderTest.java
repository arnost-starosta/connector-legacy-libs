/*
 * ====================
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (the "License").  You may not use this file
 * except in compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://opensource.org/licenses/cddl1.php
 * See the License for the specific language governing permissions and limitations
 * under the License.
 *
 * When distributing the Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://opensource.org/licenses/cddl1.php.
 * If applicable, add the following below this CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * ====================
 */
package org.identityconnectors.dbcommon;

import org.testng.annotations.Test;

import java.sql.Types;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Tests
 *
 * @version $Revision 1.0$
 * @since 1.0
 */
public class UpdateSetBuilderTest {

    private static final String MYSQL_USER_COLUMN = "User";
    private static final SQLParam VALUE = new SQLParam(MYSQL_USER_COLUMN, "name", Types.VARCHAR);

    /**
     * Test method for
     * {@link org.identityconnectors.dbcommon.UpdateSetBuilder#UpdateSetBuilder()}
     * .
     */
    @Test
    public void testUpdateSetBuilder() {
        UpdateSetBuilder actual = new UpdateSetBuilder();
        assertNotNull(actual);
        assertNotNull(actual.getParams());
    }

    /**
     * Test method for
     * {@link org.identityconnectors.dbcommon.UpdateSetBuilder#addBind(SQLParam)}
     * .
     */
    @Test
    public void testAddBindExpression() {
        UpdateSetBuilder actual = new UpdateSetBuilder();
        assertNotNull(actual);

        // do the update
        actual.addBind(new SQLParam("test1", "val1"), "password(?)");
        actual.addBind(new SQLParam("test2", "val2"), "max(?)");

        assertNotNull(actual.getSQL());
        assertEquals(actual.getSQL(), "test1 = password(?) , test2 = max(?)", "The update string");

        assertNotNull(actual.getParams());
        assertEquals(actual.getParams().size(), 2, "The count");
    }

    /**
     * Test method for
     * {@link org.identityconnectors.dbcommon.UpdateSetBuilder#getParams()}.
     */
    @Test
    public void testGetValues() {
        UpdateSetBuilder actual = new UpdateSetBuilder();
        assertNotNull(actual);

        // do the update
        actual.addBind(VALUE);

        assertNotNull(actual.getSQL());
        assertEquals(actual.getSQL(), "User = ?", "The update string");

        assertNotNull(actual.getParams());
        assertNotNull(actual.getParams().get(0));
        assertEquals(actual.getParams().get(0), VALUE, "The values");
        assertEquals(actual.getParams().get(0).getSqlType(), Types.VARCHAR, "The values");
    }

    /**
     * Test method for
     * {@link org.identityconnectors.dbcommon.UpdateSetBuilder#addBind(SQLParam)}
     */
    @Test
    public void testAddBind() {
        UpdateSetBuilder actual = new UpdateSetBuilder();
        assertNotNull(actual);

        // do the update
        actual.addBind(VALUE);

        assertNotNull(actual.getParams());
        assertEquals(actual.getParams().size(), 1, "The count");
        assertNotNull(actual.getParams().get(0));
        assertEquals(actual.getParams().get(0), VALUE, "The values");
        assertEquals(actual.getSQL(), MYSQL_USER_COLUMN + " = ?", "The update string");
    }
}
