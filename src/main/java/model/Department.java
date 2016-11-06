package model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import spring.repository.IDepartmentRepository;

import static util.Restrictions.ifNegativeFail;
import static util.Restrictions.ifNullFail;

/**
 * The class represents department's data model. Immutable, so thread-safe, this is mandatory and
 * shouldn't be changed.
 * @see IDepartmentRepository
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@Immutable
public final class Department {

    @Nonnegative
    private final int id;
    @Nonnull
    private final String name;

    /**
     * Instantiates a new Department, checks constrains as fields are annotated.
     * Some constraints are duplicated and added in the DB layer.
     *
     * @param id   The DB related unique id, not for to the human representation purposes.
     * @param name The department's name.
     */
    public Department(int id, String name) {
        this.id = ifNegativeFail(id);
        this.name = ifNullFail(name);
    }

    @Nonnegative
    public int getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        final Department department = (Department) o;
        return id == department.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
