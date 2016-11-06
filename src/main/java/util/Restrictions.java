/*
 *     Computer and algorithm interaction simulation software (CAISS).
 *     Copyright (C) 2016 Sergey Pomelov.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Conditions checkers collection.
 *
 * @author Sergey Pomelov on 15/04/2015.
 */
@SuppressWarnings({"StaticMethodOnlyUsedInOneClass", "ClassWithTooManyDependents",
        "ClassUnconnectedToPackage"})
public final class Restrictions {

    private static final Logger log = LoggerFactory.getLogger(Restrictions.class);

    private Restrictions() { /* utility class */ }

    @SuppressWarnings("ProhibitedExceptionThrown") // by design
    public static <T> T ifNullFail(T object) {
        if (object == null) {
            throw new NullPointerException("Null pointer is forbidden here!");
        }
        return object;
    }

    public static <T extends Number> T ifNegativeFail(T number) {
        if ((number == null) || (number.doubleValue() < 0.0D)) {
            throw new IllegalArgumentException("Null or negative is forbidden here!");
        }
        return number;
    }
}
