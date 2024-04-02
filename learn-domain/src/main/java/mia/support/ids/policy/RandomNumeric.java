package mia.support.ids.policy;

import mia.support.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
