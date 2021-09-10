package motherlode.base.impl;

import org.apache.logging.log4j.Level;

@FunctionalInterface
public interface LogFunction {
    void log(Level level, CharSequence message);
}
