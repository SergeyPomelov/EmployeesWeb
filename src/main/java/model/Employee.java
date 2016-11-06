package model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import static util.Restrictions.ifNegativeFail;
import static util.Restrictions.ifNullFail;

/**
 * The class represents data about particular employee. Immutability of this class is mandatory.
 * @see spring.repository.IEmployeeRepository
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings("FeatureEnvy")
@Immutable
public final class Employee {

    @Nonnegative
    private final int id;
    @Nonnull
    private final String name;
    @Nonnegative
    private final int departmentId;
    @Nullable
    private final String mail;
    @Nullable
    private final String phone;

    /**
     * Instantiates a new Employee. Checks constraints as fields are annotated.
     * Some constraints are duplicated or added in DB.
     *
     * @param id           The unique id, data base id, used only for internal app purposes.
     * @param name         The name, assumed that any employee got a name.
     * @param departmentId The department unique id, assumed that any employee belongs to some department.
     * @param mail         The mail, mail format isn't checked anywhere, mails aren't confirmed.
     * @param phone        The phone, phone format isn't checked anywhere, phones aren't confirmed.
     */
    public Employee(int id, String name, int departmentId, String mail, String phone) {
        this.id = ifNegativeFail(id);
        this.name = ifNullFail(name);
        this.departmentId = ifNegativeFail(departmentId);
        this.mail = mail;
        this.phone = phone;
    }

    @Nonnegative
    public int getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnegative
    public int getDepartmentId() {
        return departmentId;
    }

    @Nullable
    public String getMail() {
        return mail;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        final Employee employee = (Employee) o;
        return id == employee.id;
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
                .add("departmentId", departmentId)
                .add("mail", mail)
                .add("phone", phone)
                .toString();
    }
}
