package attendance_manager.utils;

import attendance_manager.domain.AbstractDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergey, created on 11/17/17.
 */
public abstract class AbstractBaseEntityDTO<T extends AbstractDomain> {

    public AbstractBaseEntityDTO() {
    }

    /* Abstract methods */
    public abstract void updateDomainEntityPlainProperties(final T domainEntity);

    /* Equals, HashCode and ToString */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractBaseEntityDTO)) {
            return false;
        }
        final EqualsBuilder builder = new EqualsBuilder();
        return builder.build();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        return builder.build();
    }
}
