package web.beanes;

import com.entities.User;

public interface Login {
public abstract User getUser();
public abstract String getUserFullName();
public abstract void refreshUser();
}
